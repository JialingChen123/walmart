package com.store.walmart.controller;

import java.util.*;

import com.store.walmart.domain.CustomerOrderRequest;
import com.store.walmart.entity.Customer;
import com.store.walmart.entity.Item;
import com.store.walmart.entity.OrderLines;
import com.store.walmart.entity.Orders;
import com.store.walmart.exception.CustomerNotFoundException;
import com.store.walmart.exception.OrderNotFoundException;
import com.store.walmart.repository.ItemRepository;
import com.store.walmart.repository.OrderLinesRepository;
import com.store.walmart.repository.CustomerRepository;
import com.store.walmart.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class Controller {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;

    private Map<Long, Integer> itemHistory = new HashMap<>();

    @Autowired
    private OrderLinesRepository orderLinesRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/customer")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer customerCreated = customerRepository.save(new Customer((customer.getName())));
        return ResponseEntity.ok(customerCreated);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Object> updateCustomerName(@RequestBody Customer customer, @PathVariable Long id) {

        Optional<Customer> customerNeedToUpdate = customerRepository.findById(id);

        if (!customerNeedToUpdate.isPresent())
            throw new CustomerNotFoundException("Customer Not Available with ID: " + id);

        customer.setId(id);
        customerRepository.save(customer);

        return ResponseEntity.ok(customer);
    }

    @PostMapping("/order")
    public ResponseEntity<Object> createOrder(@RequestBody CustomerOrderRequest customerOrderRequest) {
        Optional<Customer> customerNeedToUpdate = customerRepository.findById(customerOrderRequest.getCustomerId());

        if (!customerNeedToUpdate.isPresent())
            throw new CustomerNotFoundException("Customer Not Available with ID: " + customerOrderRequest.getCustomerId());

        List<OrderLines> result = new ArrayList<>();
        Orders orderToCreate = new Orders();
        orderToCreate.setUserId(customerOrderRequest.getCustomerId());
        Orders orderCreated = orderRepository.save(orderToCreate);
        for (OrderLines customerOrder : customerOrderRequest.getCustomerOrders()) {
            customerOrder.setOrderId(orderCreated.getId());
            result.add(customerOrder);
        }
        return ResponseEntity.ok(orderLinesRepository.saveAll(result));
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<Object> updateOrderQuantity(@RequestBody OrderLines customerOrder,
            @PathVariable Long orderId) {
        Optional<OrderLines> customerOrderUpdated = orderLinesRepository.findByOrderIdAndItemId(orderId,customerOrder.getItemId());

        if (!customerOrderUpdated.isPresent())
            throw new OrderNotFoundException("Order/Item Not Found with ID: " + orderId + "/" + customerOrder.getItemId());

        customerOrder.setOrderId(orderId);

        if(customerOrder.getQty() <= 0){
            orderLinesRepository.delete(customerOrder);
            //Clean up order table if there's no items exist
            List<Integer> orderItemCount = orderRepository.findByOrderId(orderId);
            if(orderItemCount.get(1) > 0 && orderItemCount.get(0) <=0){
                orderRepository.deleteById(orderId);
            }
        }else{
            orderLinesRepository.save(customerOrder);
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/recommendation")
    public ResponseEntity<List<Item>> getRecommendations(@RequestParam Long userId) {
        List<Item> items = itemRepository.findItemByUserId(userId);

        return ResponseEntity.ok(items);
    }

}