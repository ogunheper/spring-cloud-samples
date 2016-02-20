package com.ogunheper.microservices.controllers;

import com.ogunheper.microservices.services.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoServiceController {

    @Autowired
    private PropertiesService propertiesService;

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public String username() {
        return propertiesService.getUsername();
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password() {
        return propertiesService.getPassword();
    }
}
