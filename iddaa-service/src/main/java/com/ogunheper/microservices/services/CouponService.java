package com.ogunheper.microservices.services;

import com.ogunheper.microservices.clients.FinanceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CouponService {

    @Autowired
    private FinanceClient financeClient;

    public void createCoupon() {
        log.info(financeClient.who());
    }
}
