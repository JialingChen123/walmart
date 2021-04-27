package com.store.walmart.entity;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderLinesId implements Serializable {
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_id")
    private Long itemId;

    // default constructor
    public OrderLinesId(Long orderId, Long itemId) {
        this.orderId = orderId;
        this.itemId = itemId;
    }

    public OrderLinesId() {
    }

}
