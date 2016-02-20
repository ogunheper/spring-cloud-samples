package com.ogunheper.microservices.services;

import com.ogunheper.microservices.configuration.DemoServiceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertiesService {

    @Autowired
    private DemoServiceProperties demoServiceProperties;

    public String getUsername() {
        return demoServiceProperties.getUsername();
    }

    public String getPassword() {
        return demoServiceProperties.getPassword();
    }
}
