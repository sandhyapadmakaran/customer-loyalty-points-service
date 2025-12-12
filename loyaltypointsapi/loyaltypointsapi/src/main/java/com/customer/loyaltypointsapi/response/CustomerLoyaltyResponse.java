package com.customer.loyaltypointsapi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomerLoyaltyResponse {
    private long id;
    private String name;
    private double points;
    private String tier;

    public CustomerLoyaltyResponse(long id, String name, double points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }
}
