package com.ogunheper.microservices.controllers;

import com.ogunheper.microservices.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@RestController
public class DemoServiceController {

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username() {
        return propertiesService.getUsername();
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password() {
        return propertiesService.getPassword();
    }

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public String who() {
        return ManagementFactory.getRuntimeMXBean().getName() + " " + applicationContext.getId();
    }
}
