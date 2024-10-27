package org.speculatingwook.shopping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
                .filter(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice)
                        .sum() > minTotal)
                .collect(Collectors.toList());
    }

    // 각 고객별 총 주문 금액을 계산합니다.
    public Map<String, Double> calculateTotalOrderValuePerCustomer() {
        return orders.stream().collect(Collectors.groupingBy(
                Order::getCustomerId,
                Collectors.summingDouble(o -> o.getProducts()
                        .stream()
                        .mapToDouble(Product::getPrice)
                        .sum())
        ));
    }

    // 가장 많이 주문된 제품을 찾습니다.
    // 일단 제대로 된 값이 나올 수 있도록 streamAPI를 사용해서 작성해보자. 이 코드에서 발생할 수 있는 문제가 있을까?
    // A : 주문이 많아지면,  ...map(Order::getProducts).flatMap(List::stream)... 에서 성능이 저하될 수 있다
    public Product findMostOrderedProduct() {
        return orders.stream()
                .map(Order::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // 특정 기간 동안의 일일 매출을 계산합니다.
    public Map<LocalDate, Double> calculateDailySales(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(o -> !o.getOrderDate().isBefore(startDate) &&
                        !o.getOrderDate().isAfter(endDate))//이상 이하 초과 미만 신경써야 함...
                .collect(Collectors.groupingBy(
                        Order::getOrderDate,//날짜 기준으로 key값을 잡음.
                        Collectors.summingDouble(order -> order.getProducts()
                                .stream()
                                .mapToDouble(Product::getPrice)
                                .sum())
                ));
    }

    // 주문 상태를 업데이트합니다.
    public void updateOrderStatus(String orderId, UnaryOperator<String> statusUpdater) {
        orders.stream()
                .filter(o -> o.getId().equals(orderId))
                .findFirst()
                .ifPresent(o -> o.setStatus(statusUpdater.apply(o.getStatus())));

    }

    // 조건에 맞는 주문들의 특정 정보를 추출합니다.
    public <T> List<T> extractOrderInfo(Predicate<Order> filter, Function<Order, T> infoExtractor) {
        return orders.stream()
                .filter(filter)
                .map(infoExtractor)
                .collect(Collectors.toList());
    }

    // 각 카테고리별 판매 수량을 계산합니다.
    public Map<String, Long> countSalesByCategory() {
        return orders.stream()
                .map(Order::getProducts)
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.counting()
                ));
    }

    // 주어진 기간 동안 가장 많은 매출을 올린 고객을 찾습니다.
    // 질문: String은 이 함수에서 사용하기 좋은 타입인가? 만약 아니라면 어떻게 바꾸는 게 더 좋을까?
    public String findTopCustomer(LocalDate startDate, LocalDate endDate) {
        return orders.stream()
                .filter(o -> !o.getOrderDate().isAfter(endDate) &&
                        !o.getOrderDate().isBefore(startDate))
                .collect(Collectors.groupingBy(
                        Order::getCustomerId,
                        Collectors.summingDouble(o -> o.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum())
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    // 모든 주문에 대해 주어진 작업을 수행합니다.
    public void processOrders(Consumer<Order> orderProcessor) {
        orders.forEach(orderProcessor);
    }

    // 주문들을 특정 기준으로 정렬합니다.
    public List<Order> sortOrders(Comparator<Order> orderComparator) {
        return orders.stream()
                .sorted(orderComparator)
                .collect(Collectors.toList());
    }
}