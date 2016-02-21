package com.ogunheper.microservices.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "finance-service")
public interface FinanceClient {

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    String who();
}
