package com.store.walmart.exception;

public class CustomerNotFoundException extends RuntimeException {
	public CustomerNotFoundException(String exception) {
		super(exception);
	}
}
