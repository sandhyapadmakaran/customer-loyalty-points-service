package com.customer.loyaltypointsapi.controller;


import com.customer.loyaltypointsapi.constants.LoyaltyConstants;
import com.customer.loyaltypointsapi.dto.Customers;
import com.customer.loyaltypointsapi.request.CustomerLoyaltyRequest;
import com.customer.loyaltypointsapi.response.CustomerLoyaltyResponse;
import com.customer.loyaltypointsapi.service.CustomerLoyaltyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerLoyaltyControllerTest {
    private CustomerLoyaltyController customerLoyaltyController;
    private CustomerLoyaltyService customerLoyaltyService;

    @BeforeEach
    void setUp() {
        customerLoyaltyService = mock(CustomerLoyaltyService.class);
        customerLoyaltyController = new CustomerLoyaltyController();
        customerLoyaltyController.customerLoyaltyService = customerLoyaltyService;
    }

    @Test
    void getTopLoyaltyCustomers() {
        List<Customers> customersList = Arrays.asList(
                new Customers(1, "Ravi", LoyaltyConstants.REGULAR, 5000),
                new Customers(2, "Anita", LoyaltyConstants.PREMIUM, 25000),
                new Customers(3, "Manoj", LoyaltyConstants.REGULAR, 70000));
        CustomerLoyaltyRequest customerLoyaltyRequest = new CustomerLoyaltyRequest();
        customerLoyaltyRequest.setCustomers(customersList);
        List<CustomerLoyaltyResponse> customerLoyaltyResponses = Arrays.asList(
                new CustomerLoyaltyResponse(3, "Manoj", 700),
                new CustomerLoyaltyResponse(2, "Anita", 300),
                new CustomerLoyaltyResponse(1, "Manoj", 50));
        when(customerLoyaltyService.getTopLoyaltyCustomers(customerLoyaltyRequest)).thenReturn(customerLoyaltyResponses);
        ResponseEntity<List<CustomerLoyaltyResponse>> customerLoyaltyResponses1 = customerLoyaltyController.getTopLoyaltyCustomers(customerLoyaltyRequest);
        Assertions.assertNotNull(customerLoyaltyResponses1);
    }
}
