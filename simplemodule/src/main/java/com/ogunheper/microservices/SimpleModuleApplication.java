package com.ogunheper.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SimpleModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleModuleApplication.class, args);
    }
}
