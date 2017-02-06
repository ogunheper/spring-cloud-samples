package com.ogunheper.microservices.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ogunheper.microservices.clients.DemoClient;
import com.ogunheper.microservices.clients.IddaaClient;
import com.ogunheper.microservices.clients.PetStoreSwaggerClient;
import com.ogunheper.microservices.services.CouponService;
import com.ogunheper.microservices.services.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.util.Collection;

@Slf4j
@RestController
public class IddaaController {

    @Autowired
    private CouponService couponService;

    @Autowired
    private DemoClient demoClient;

    @Autowired
    private PetStoreSwaggerClient petStoreSwaggerClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private IddaaClient iddaaClient;

    @Autowired
    private ProgramService programService;

    @RequestMapping(value = "/coupon", method = RequestMethod.POST)
    public void create() {
        couponService.createCoupon();
    }

    @RequestMapping(value = "/consume-demo-service-who", method = RequestMethod.GET)
    public String consumeDemoServiceWho() {
        return demoClient.who();
    }

    @RequestMapping(value = "/swagger-petshop", method = RequestMethod.GET)
    public String consumeSwaggerPetShop() {
        return petStoreSwaggerClient.swaggerJson();
    }

    @RequestMapping(value = "/kim", method = RequestMethod.GET)
    public String kim() {
        return iddaaClient.who();
    }

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public String who(@RequestHeader(value = "X-UserId", defaultValue = "") String userId) {
        log.info("In IddaaController WHO; userId: {}", userId);
        return ManagementFactory.getRuntimeMXBean().getName() + " " + applicationContext.getId();
    }

    @HystrixCommand(
            commandKey = "eventsThreadCommand",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD")
            },
            threadPoolKey = "eventsThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "4")
            }
    )
    @RequestMapping(value = "/eventsThread", method = RequestMethod.GET)
    public Collection<String> eventsThread() {
        log.info("Running events thread command");
        return programService.getListOfActiveEvents();
    }

    @HystrixCommand(
            commandKey = "eventsSemaphoreCommand",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            }
    )
    @RequestMapping(value = "/eventsSemaphore", method = RequestMethod.GET)
    public Collection<String> eventsSemaphore() {
        log.info("Running events semaphore command");
        return programService.getListOfActiveEvents();
    }
}
