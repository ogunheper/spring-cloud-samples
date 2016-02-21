package com.ogunheper.microservices.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://petstore.swaggerJson.io/v2")
public interface PetStoreSwaggerClient {

    @RequestMapping(value = "/swagger.json", method = RequestMethod.GET)
    String swaggerJson();
}
