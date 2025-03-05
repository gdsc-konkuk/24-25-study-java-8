package org.speculatingwook.shopping;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.summingDouble;

public class Order {
    private String id;
    private LocalDate orderDate;
    private String customerId;
    private List<Product> products;
    private String status;

    public Order(String id, LocalDate orderDate, String customerId, List<Product> products, String status) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.products = products;
        this.status = status;
    }

    public String getId() { return id; }
    public LocalDate getOrderDate() { return orderDate; }
    public String getCustomerId() { return customerId; }
    public List<Product> getProducts() { return products; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getTotal(){
        return products.stream()
                .collect(summingDouble(Product::getPrice));
    }
}
