package com.customer.loyaltypointsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customers {
    private long id;
    private String name;
    private String type;
    private double totalPurchase;
}
