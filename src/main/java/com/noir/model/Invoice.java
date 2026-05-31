package com.noir.model;

import java.util.List;

public class Invoice {
    private String id;
    private String orderNumber;
    private String clientId;
    private String customerName;
    private String customerEmail;
    private String createdAt;
    private String status; // paid, pending
    private List<Order.OrderItem> items;
    private Double total;
    private String deliveryAddress;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Order.OrderItem> getItems() { return items; }
    public void setItems(List<Order.OrderItem> items) { this.items = items; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }
}