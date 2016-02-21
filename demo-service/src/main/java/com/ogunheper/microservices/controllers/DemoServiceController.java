package com.ogunheper.microservices.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ogunheper.microservices.configuration.DemoServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
public class DemoServiceController {

    @Autowired
    private DemoServiceProperties demoServiceProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username() {
        return demoServiceProperties.getUsername();
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password() {
        return demoServiceProperties.getPassword();
    }

    @HystrixCommand
    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public String who() {
        return ManagementFactory.getRuntimeMXBean().getName() + " " + applicationContext.getId();
    }
}
