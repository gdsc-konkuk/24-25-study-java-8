package org.speculatingwook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.speculatingwook.shopping.Order;
import org.speculatingwook.shopping.OrderProcessingService;
import org.speculatingwook.shopping.Product;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.*;

/**
 * 복습 겸 지금까지 배운 것들을 활용해보자.
 */
public class OrderProcessingServiceTest {

    private OrderProcessingService service;

    @BeforeEach
    void setUp() {
        service = new OrderProcessingService();

        // 제품 생성
        Product p1 = new Product("P1", "Laptop", 1000.0, "Electronics");
        Product p2 = new Product("P2", "Smartphone", 500.0, "Electronics");
        Product p3 = new Product("P3", "T-shirt", 20.0, "Clothing");
        Product p4 = new Product("P4", "Headphones", 100.0, "Electronics");
        Product p5 = new Product("P5", "Book", 15.0, "Books");
        Product p6 = new Product("P6", "Tablet", 300.0, "Electronics");
        Product p7 = new Product("P7", "Jeans", 50.0, "Clothing");
        Product p8 = new Product("P8", "Watch", 200.0, "Accessories");

        // 주문 생성
        Order o1 = new Order("O1", LocalDate.of(2023, 1, 1), "C1", Arrays.asList(p1, p2), "Completed");
        Order o2 = new Order("O2", LocalDate.of(2023, 1, 2), "C2", Arrays.asList(p2, p3, p4), "Processing");
        Order o3 = new Order("O3", LocalDate.of(2023, 1, 3), "C1", Arrays.asList(p3, p5), "Completed");
        Order o4 = new Order("O4", LocalDate.of(2023, 1, 4), "C3", Arrays.asList(p6, p7, p8), "Shipped");
        Order o5 = new Order("O5", LocalDate.of(2023, 1, 5), "C2", Arrays.asList(p1, p4, p5), "Completed");
        Order o6 = new Order("O6", LocalDate.of(2023, 1, 6), "C4", Arrays.asList(p2, p7), "Processing");
        Order o7 = new Order("O7", LocalDate.of(2023, 1, 7), "C1", Arrays.asList(p8, p3, p4), "Shipped");

        // 주문 추가
        service.addOrder(o1);
        service.addOrder(o2);
        service.addOrder(o3);
        service.addOrder(o4);
        service.addOrder(o5);
        service.addOrder(o6);
        service.addOrder(o7);
    }

    @Test
    void testFindHighValueOrders() {
        List<Order> highValueOrders = service.findHighValueOrders(1000.0);
        assertEquals(2, highValueOrders.size());
        assertTrue(highValueOrders.stream().anyMatch(o -> o.getId().equals("O1")));
        assertTrue(highValueOrders.stream().anyMatch(o -> o.getId().equals("O5")));
    }

    @Test
    void testCalculateTotalOrderValuePerCustomer() {
        Map<String, Double> totalValues = service.calculateTotalOrderValuePerCustomer();
        assertEquals(1855.0, totalValues.get("C1"), 0.01);
        assertEquals(1735.0, totalValues.get("C2"), 0.01);
        assertEquals(550.0, totalValues.get("C3"), 0.01);
        assertEquals(550.0, totalValues.get("C4"), 0.01);
    }

    @Test
    void testFindMostOrderedProduct() {
        Product mostOrdered = service.findMostOrderedProduct();
        assertTrue(mostOrdered != null);
        assertEquals("T-shirt", mostOrdered.getName());
    }

    @Test
    void testCalculateDailySales() {
        Map<LocalDate, Double> dailySales = service.calculateDailySales(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 7)
        );
        assertEquals(1500.0, dailySales.get(LocalDate.of(2023, 1, 1)), 0.01);
        assertEquals(620.0, dailySales.get(LocalDate.of(2023, 1, 2)), 0.01);
        assertEquals(35.0, dailySales.get(LocalDate.of(2023, 1, 3)), 0.01);
        assertEquals(550.0, dailySales.get(LocalDate.of(2023, 1, 4)), 0.01);
        assertEquals(1115.0, dailySales.get(LocalDate.of(2023, 1, 5)), 0.01);
        assertEquals(550.0, dailySales.get(LocalDate.of(2023, 1, 6)), 0.01);
        assertEquals(320.0, dailySales.get(LocalDate.of(2023, 1, 7)), 0.01);
    }

    @Test
    void testUpdateOrderStatus() {
        service.updateOrderStatus("O2", status -> "Shipped");
        Optional<Order> updatedOrder = service.extractOrderInfo(
                order -> order.getId().equals("O2"),
                order -> order
        ).stream().findFirst();
        assertTrue(updatedOrder.isPresent());
        assertEquals("Shipped", updatedOrder.get().getStatus());
    }

    @Test
    void testExtractOrderInfo() {
        List<String> customerIds = service.extractOrderInfo(
                order -> order.getStatus().equals("Completed"),
                Order::getCustomerId
        );
        assertEquals(3, customerIds.size());
        assertTrue(customerIds.contains("C1"));
        assertTrue(customerIds.contains("C2"));
    }

    @Test
    void testCountSalesByCategory() {
        Map<String, Long> salesByCategory = service.countSalesByCategory();
        assertEquals(9L, salesByCategory.get("Electronics"));
        assertEquals(5L, salesByCategory.get("Clothing"));
        assertEquals(2L, salesByCategory.get("Books"));
        assertEquals(2L, salesByCategory.get("Accessories"));
    }

    @Test
    void testFindTopCustomer() {
        String topCustomer = service.findTopCustomer(
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2023, 1, 7)
        );
        assertFalse(topCustomer.isEmpty());
        assertEquals("C1", topCustomer);
    }

    @Test
    void testSortOrders() {
        List<Order> sortedOrders = service.sortOrders(
                Comparator.comparing(Order::getOrderDate)
        );
        assertEquals(7, sortedOrders.size());
        assertEquals("O1", sortedOrders.get(0).getId());
        assertEquals("O7", sortedOrders.get(6).getId());
    }
}