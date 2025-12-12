package com.customer.loyaltypointsapi.service;

import com.customer.loyaltypointsapi.constants.LoyaltyConstants;
import com.customer.loyaltypointsapi.dto.Customers;
import com.customer.loyaltypointsapi.exception.InvalidRequestException;
import com.customer.loyaltypointsapi.request.CustomerLoyaltyRequest;
import com.customer.loyaltypointsapi.response.CustomerLoyaltyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CustomerLoyaltyServiceTest {
    private CustomerLoyaltyService customerLoyaltyService;

    @BeforeEach
    void setUp() {
        customerLoyaltyService = new CustomerLoyaltyService();
    }

    @Test
    void getTopLoyaltyCustomers() {
        List<Customers> customersList = Arrays.asList(
                new Customers(1, "Ravi", LoyaltyConstants.REGULAR, 5000),
                new Customers(2, "Anita", LoyaltyConstants.PREMIUM, 25000),
                new Customers(3, "Manoj", LoyaltyConstants.REGULAR, 70000));
        CustomerLoyaltyRequest customerLoyaltyRequest = new CustomerLoyaltyRequest();
        customerLoyaltyRequest.setCustomers(customersList);
        List<CustomerLoyaltyResponse> customerLoyaltyResponses = customerLoyaltyService.getTopLoyaltyCustomers(customerLoyaltyRequest);
        Assertions.assertNotNull(customerLoyaltyResponses);
    }

    @Test
    void getTopLoyaltyCustomersWithEmptyTier() {
        List<Customers> customersList = Arrays.asList(
                new Customers(1, "Ravi", LoyaltyConstants.REGULAR, 500),
                new Customers(2, "Anita", LoyaltyConstants.PREMIUM, 2500),
                new Customers(3, "Manoj", LoyaltyConstants.REGULAR, 7000));
        CustomerLoyaltyRequest customerLoyaltyRequest = new CustomerLoyaltyRequest();
        customerLoyaltyRequest.setCustomers(customersList);
        List<CustomerLoyaltyResponse> customerLoyaltyResponses = customerLoyaltyService.getTopLoyaltyCustomers(customerLoyaltyRequest);
        Assertions.assertNotNull(customerLoyaltyResponses);
    }

    @Test
    void getTopLoyaltyCustomersWithInvalidRequest() {
        CustomerLoyaltyRequest customerLoyaltyRequest = new CustomerLoyaltyRequest();
        customerLoyaltyRequest.setCustomers(Collections.emptyList());
        Assertions.assertThrows(InvalidRequestException.class, () -> {
            customerLoyaltyService.getTopLoyaltyCustomers(customerLoyaltyRequest);
        });
    }

}
