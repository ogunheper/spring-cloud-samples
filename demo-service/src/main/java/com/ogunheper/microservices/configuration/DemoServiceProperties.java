package com.ogunheper.microservices.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "demo.service")
public class DemoServiceProperties {

    private String username;
    private String password;
}
