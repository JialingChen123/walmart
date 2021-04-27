package com.store.walmart;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.walmart.domain.CustomerOrderRequest;
import com.store.walmart.entity.Item;
import com.store.walmart.entity.OrderLines;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class RecommendationAcceptanceTest {

    private RestTemplate restTemplate;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testRecommendation() throws JsonProcessingException {
        restTemplate = new RestTemplate();
        // fetching the recommendation for the user 1
        ArrayList<Item> initialRecommendations = restTemplate
                .getForObject("http://localhost:8080/customer/recommendation?userId=1", ArrayList.class);

        //Creating a customer order with the item_id present in the recommendation 
        Long itemId = 12L;
        CustomerOrderRequest orderRequest = new CustomerOrderRequest();
        orderRequest.setCustomerId(1L);
        List<OrderLines> orderLinesList = new ArrayList<>();
        OrderLines orderLines = new OrderLines();
        orderLines.setItemId(itemId);
        orderLines.setQty(1L);
        orderLinesList.add((orderLines));
        orderRequest.setCustomerOrders(orderLinesList);
        String inputJson = mapToJson(orderRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(inputJson, headers);

        //created the order with the item_id present in the recommendation list
        String orderCreated = restTemplate.postForObject("http://localhost:8080/order", request, String.class);

        //Getting the recommendation for the customer
        List<LinkedHashMap> itemAfterUpdate = restTemplate
                .getForObject("http://localhost:8080/customer/recommendation?userId=1", ArrayList.class);

        //Based on his purchase he will not be seeing the item id that he has created the order with
        //asserting to make sure its not equals to the item he created       
        for (LinkedHashMap item : itemAfterUpdate) {
            assertNotEquals(itemId, item.get("id"));
        }
    }

}