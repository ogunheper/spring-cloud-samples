package com.ogunheper.microservices.controllers;

import com.ogunheper.microservices.clients.DemoServiceClient;
import com.ogunheper.microservices.clients.IddaaServiceClient;
import com.ogunheper.microservices.clients.PetStoreSwaggerClient;
import com.ogunheper.microservices.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
public class IddaaController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private DemoServiceClient demoServiceClient;

    @Autowired
    private PetStoreSwaggerClient petStoreSwaggerClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IddaaServiceClient iddaaServiceClient;

    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    public void create() {
        couponService.createCoupon();
    }

    @RequestMapping(value = "/consume-demo-service-who", method = RequestMethod.GET)
    public String consumeDemoServiceWho() {
        return demoServiceClient.who();
    }

    @RequestMapping(value = "/swagger-petshop", method = RequestMethod.GET)
    public String consumeSwaggerPetShop() {
        return petStoreSwaggerClient.swaggerJson();
    }

    @RequestMapping(value = "/kim", method = RequestMethod.GET)
    public String kim() {
        return iddaaServiceClient.who();
    }

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public String who(@RequestHeader(value = "X-UserId", defaultValue = "") String userId) {
        System.out.println("In IddaaController WHO - " + userId);
        return ManagementFactory.getRuntimeMXBean().getName() + " " + applicationContext.getId();
    }
}
