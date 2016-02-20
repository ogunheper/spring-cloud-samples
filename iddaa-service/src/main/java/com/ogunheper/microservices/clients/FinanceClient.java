package com.ogunheper.microservices.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "demo-service")
public interface FinanceClient {

    @RequestMapping(value = "username", method = RequestMethod.GET)
    String username();
}
