package com.store.walmart.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String exception) {
        super(exception);
    }
}
