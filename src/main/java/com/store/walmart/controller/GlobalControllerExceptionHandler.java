package com.store.walmart.controller;

import com.store.walmart.exception.CustomerNotFoundException;
import com.store.walmart.exception.OrderNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleException(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity(customerNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleException(OrderNotFoundException orderNotFoundException) {
        return new ResponseEntity(orderNotFoundException.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity(methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(), new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
}