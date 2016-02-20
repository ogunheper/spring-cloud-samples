package com.ogunheper.microservices.controllers;

import com.ogunheper.microservices.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/iddaa")
@RestController
public class IddaaController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    public void create() {
        couponService.createCoupon();
    }
}
