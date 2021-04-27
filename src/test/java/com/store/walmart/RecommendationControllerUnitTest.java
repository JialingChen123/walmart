package com.store.walmart;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.walmart.domain.CustomerOrderRequest;
import com.store.walmart.entity.Customer;
import com.store.walmart.entity.Item;
import com.store.walmart.entity.OrderLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WalmartApplication.class)
public class ControllerTest {

    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    // create customer tests
    @Test
    public void should_create_new_customer_with_200_OK() throws Exception{
        String uri = "/customer";
        Customer customerRequest = new Customer();
        customerRequest.setName("TEST");

        String inputJson = mapToJson(customerRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Customer customerResponse = mapFromJson(mvcResult.getResponse().getContentAsString(),Customer.class);
        assertEquals(customerRequest.getName(), customerResponse.getName());
    }

    @Test
    public void should_not_create_new_customer_with_invalid_request_and_return_400_bad_request() throws Exception{
        String uri = "/customer";
        Customer customerRequest = new Customer();
        customerRequest.setName("");

        String inputJson = mapToJson(customerRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
        String customerResponse = mvcResult.getResponse().getContentAsString();
        assertEquals("Name are required field", customerResponse);
    }

    // update customer tests
    @Test
    public void should_update_customer_name_with_200_OK() throws Exception{
        String uri = "/customer" + "/1";
        Customer customerRequest = new Customer();
        customerRequest.setName("Update customer name");

        String inputJson = mapToJson(customerRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        Customer customerResponse = mapFromJson(mvcResult.getResponse().getContentAsString(),Customer.class);
        assertEquals(customerRequest.getName(), customerResponse.getName());
    }

    @Test
    public void should_not_update_customer_name_with_invalid_customerId_and_return_404_not_found() throws Exception{
        String uri = "/customer" + "/12";
        Customer customerRequest = new Customer();
        customerRequest.setName("Update customer name");

        String inputJson = mapToJson(customerRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String customerResponse = mvcResult.getResponse().getContentAsString();
        assertEquals("Customer Not Available with ID: 12", customerResponse);
    }

    // create order tests
    @Test
    public void should_create_order_with_200_OK() throws Exception{
        String uri = "/order";
        CustomerOrderRequest orderRequest = new CustomerOrderRequest();
        orderRequest.setCustomerId(1L);
        List<OrderLines> orderLinesList = new ArrayList<>();
        OrderLines orderLines = new OrderLines();
        orderLines.setItemId(1L);
        orderLines.setQty(1L);
        orderLinesList.add((orderLines));
        orderRequest.setCustomerOrders(orderLinesList);
        String inputJson = mapToJson(orderRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        List<OrderLines> orderResponse = mapFromJson(mvcResult.getResponse().getContentAsString(),List.class);
        assertNotNull(orderResponse);
    }

    @Test
    public void should_not_create_order_with_invalid_customerId_and_return_404_not_found() throws Exception{
        String uri = "/order";
        CustomerOrderRequest orderRequest = new CustomerOrderRequest();
        orderRequest.setCustomerId(12L);
        List<OrderLines> orderLinesList = new ArrayList<>();
        OrderLines orderLines = new OrderLines();
        orderLines.setItemId(1L);
        orderLines.setQty(1L);
        orderLinesList.add((orderLines));
        orderRequest.setCustomerOrders(orderLinesList);
        String inputJson = mapToJson(orderRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String orderResponse = mvcResult.getResponse().getContentAsString();
        assertEquals("Customer Not Available with ID: 12",orderResponse);
    }

    // update order tests
    @Test
    public void should_update_order_qty_with_204_not_content() throws Exception{
        String uri = "/order" + "/1";
        OrderLines orderRequest = new OrderLines();
        orderRequest.setItemId(7L);
        orderRequest.setQty(10L);
        String inputJson = mapToJson(orderRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void should_delete_item_with_204_not_content() throws Exception{
        String uri = "/order" + "/1";
        OrderLines orderRequest = new OrderLines();
        orderRequest.setItemId(3L);
        orderRequest.setQty(10L);
        String inputJson = mapToJson(orderRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(204, status);
    }

    @Test
    public void should_not_update_order_qty_with_invalid_order_id_and_return_404() throws Exception{
        String uri = "/order" + "/10";
        OrderLines orderRequest = new OrderLines();
        orderRequest.setItemId(3L);
        orderRequest.setQty(10L);
        String inputJson = mapToJson(orderRequest);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String orderResponse = mvcResult.getResponse().getContentAsString();
        assertEquals("Order/Item Not Found with ID: 10/3",orderResponse);
    }

    // get recommendation tests
    @Test
    public void should_get_recommendation_for_user_with_200_ok() throws Exception{
        String uri = "/customer/recommendation";

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("userId","1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        List<Item> response = mapFromJson(mvcResult.getResponse().getContentAsString(),List.class);
        assertNotNull(response);
        assertEquals(3,response.size());
    }
}