package com.ogunheper.microservices.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "demo.service")
public class DemoProperties {

    private String username;
    private String password;
}
