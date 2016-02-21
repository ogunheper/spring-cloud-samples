package com.ogunheper.microservices.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "iddaa-service")
public interface IddaaServiceClient {

    @RequestMapping(value = "/who", method = RequestMethod.GET)
    String who();
}
