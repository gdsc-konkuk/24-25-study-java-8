package org.speculatingwook.shopping;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class OrderProcessingService {
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    // 주문 총액이 특정 금액 이상인 주문들을 찾습니다.
    public List<Order> findHighValueOrders(double minTotal) {
        return orders.stream()
                .filter(item -> item.getProducts().stream().collect(Collectors.summingDouble(Product::getPrice)) > minTotal)
                .toList();
    }

    // 각 고객별 총 주문 금액을 계산합니다.
    public Map<String, Double> calculateTotalOrderValuePerCustomer() {
        return orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerId,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .collect(Collectors.summingDouble(Product::getPrice)))));
    }


    // 가장 많이 주문된 제품을 찾습니다.
    // 일단 제대로 된 값이 나올 수 있도록 streamAPI를 사용해서 작성해보자. 이 코드에서 발생할 수 있는 문제가 있을까?
    public Product findMostOrderedProduct() {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(product -> product, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new NoSuchElementException("No product found"));
    }


    // 특정 기간 동안의 일일 매출을 계산합니다.
    public Map<LocalDate, Double> calculateDailySales(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(item -> !item.getOrderDate().isBefore(startDate) && !item.getOrderDate().isAfter(endDate))  // startDate와 endDate 포함
                .collect(Collectors.groupingBy(Order::getOrderDate,
                        Collectors.summingDouble(order -> order.getProducts().stream()
                                .collect(Collectors.summingDouble(Product::getPrice)))));
    }


    // 주문 상태를 업데이트합니다.
    public void updateOrderStatus(String orderId, UnaryOperator<String> statusUpdater) {
        orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .ifPresent(order -> order.setStatus(statusUpdater.apply(order.getStatus())));
    }


    // 조건에 맞는 주문들의 특정 정보를 추출합니다.
    public <T> List<T> extractOrderInfo(Predicate<Order> filter, Function<Order, T> infoExtractor) {
        return orders.stream().filter(filter).map(infoExtractor).toList();
    }

    // 각 카테고리별 판매 수량을 계산합니다.
    public Map<String, Long> countSalesByCategory() {
        return orders.stream()
                .flatMap(order -> order.getProducts().stream())
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    // 주어진 기간 동안 가장 많은 매출을 올린 고객을 찾습니다.
    public String findTopCustomer(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(order -> !order.getOrderDate().isBefore(startDate) && !order.getOrderDate().isAfter(endDate))
                .collect(Collectors.groupingBy(Order::getCustomerId,
                        Collectors.summingDouble(item->item.getProducts().stream()
                                .collect(Collectors.summingDouble(Product::getPrice)))))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
    }
    // 질문: String은 이 함수에서 사용하기 좋은 타입인가? 만약 아니라면 어떻게 바꾸는 게 더 좋을까?


    // 모든 주문에 대해 주어진 작업을 수행합니다.
    public void processOrders(Consumer<Order> orderProcessor) {
        orders.forEach(orderProcessor);
    }

    // 주문들을 특정 기준으로 정렬합니다.
    public List<Order> sortOrders(Comparator<Order> orderComparator) {
        return orders.stream().sorted(orderComparator).toList();
    }
}