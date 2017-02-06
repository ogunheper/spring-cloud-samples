package com.ogunheper.microservices.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.ogunheper.microservices.clients.UserServiceClient;
import com.ogunheper.microservices.configuration.DemoProperties;
import com.ogunheper.microservices.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;

@Slf4j
@RestController
public class DemoController {

    @Autowired
    private DemoProperties demoProperties;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserServiceClient userServiceClient;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username() {
        return demoProperties.getUsername();
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password() {
        return demoProperties.getPassword();
    }

    @HystrixCommand(
            threadPoolKey = "DemoController.whoThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "1")
            }
    )
    @RequestMapping(value = "/who", method = RequestMethod.GET)
    public String who() {
        log.info("Running who thread command");
        return ManagementFactory.getRuntimeMXBean().getName() + " " + applicationContext.getId();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long id) {
        return userServiceClient.getUser(id);
    }
}
