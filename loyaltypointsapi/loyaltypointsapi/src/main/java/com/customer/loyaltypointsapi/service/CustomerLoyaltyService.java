package com.customer.loyaltypointsapi.service;

import com.customer.loyaltypointsapi.constants.LoyaltyConstants;
import com.customer.loyaltypointsapi.exception.InvalidRequestException;
import com.customer.loyaltypointsapi.request.CustomerLoyaltyRequest;
import com.customer.loyaltypointsapi.response.CustomerLoyaltyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerLoyaltyService {
    private static final Logger log = LoggerFactory.getLogger(CustomerLoyaltyService.class);

    public List<CustomerLoyaltyResponse> getTopLoyaltyCustomers(CustomerLoyaltyRequest customerLoyaltyRequest) {
        log.info("CustomerLoyaltyService::getTopLoyaltyCustomers::{}", customerLoyaltyRequest);
        if (customerLoyaltyRequest == null || customerLoyaltyRequest.getCustomers().isEmpty()) {
            throw new InvalidRequestException("Invalid Request");
        }
        List<CustomerLoyaltyResponse> loyaltyReportList = getReportList(customerLoyaltyRequest);
        return loyaltyReportList.stream().sorted(Comparator.comparingDouble(CustomerLoyaltyResponse::getPoints).reversed()).limit(3).collect(Collectors.toList());
    }

    private List<CustomerLoyaltyResponse> getReportList(CustomerLoyaltyRequest customerLoyaltyRequest) {
        return customerLoyaltyRequest.getCustomers().stream().map(n -> {
            double pointsEarned = n.getTotalPurchase() / 100;
            if (LoyaltyConstants.PREMIUM.equalsIgnoreCase(n.getType())) {
                pointsEarned = pointsEarned + pointsEarned * 20 / 100;
            }
            String tier = getTierValue(pointsEarned);
            return new CustomerLoyaltyResponse(n.getId(), n.getName(), pointsEarned, tier);
        }).toList();
    }

    private String getTierValue(double pointsEarned) {
        if (pointsEarned < 100) {
            return LoyaltyConstants.BRONZE;
        }
        if (pointsEarned <= 500) {
            return LoyaltyConstants.SILVER;
        }
        return LoyaltyConstants.GOLD;
    }
}