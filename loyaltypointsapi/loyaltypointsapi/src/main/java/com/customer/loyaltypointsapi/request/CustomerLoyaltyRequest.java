package com.customer.loyaltypointsapi.request;

import com.customer.loyaltypointsapi.dto.Customers;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CustomerLoyaltyRequest {
    private List<Customers> customers;
}
