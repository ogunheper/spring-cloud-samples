package com.ogunheper.microservices.services;

import com.ogunheper.microservices.clients.FinanceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponService {

    @Autowired
    private FinanceClient financeClient;

    public void createCoupon() {
        System.out.println(financeClient.username());
    }
}
