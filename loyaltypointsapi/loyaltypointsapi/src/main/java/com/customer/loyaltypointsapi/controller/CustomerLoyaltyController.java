package com.customer.loyaltypointsapi.controller;

import com.customer.loyaltypointsapi.request.CustomerLoyaltyRequest;
import com.customer.loyaltypointsapi.response.CustomerLoyaltyResponse;
import com.customer.loyaltypointsapi.service.CustomerLoyaltyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerLoyaltyController {
    private static final Logger log = LoggerFactory.getLogger(CustomerLoyaltyController.class);

    @Autowired
    CustomerLoyaltyService customerLoyaltyService;

    @PostMapping("/loyalty")
    public ResponseEntity<List<CustomerLoyaltyResponse>> getTopLoyaltyCustomers(@RequestBody CustomerLoyaltyRequest customerLoyaltyRequest) {
        log.info("CustomerLoyaltyController::getTopLoyaltyCustomers::{}", customerLoyaltyRequest);
        return ResponseEntity.ok(customerLoyaltyService.getTopLoyaltyCustomers(customerLoyaltyRequest));
    }
}
