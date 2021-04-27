package com.store.walmart.domain;

import java.util.List;

import com.store.walmart.entity.OrderLines;

public class CustomerOrderRequest {
    private List<OrderLines> customerOrders;
    private Long customerId;

    public List<OrderLines> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<OrderLines> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
