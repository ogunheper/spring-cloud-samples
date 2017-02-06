package com.ogunheper.microservices.clients;

import com.ogunheper.microservices.contracts.UserServiceContract;
import com.ogunheper.microservices.models.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "user-service")
public interface UserServiceClient extends UserServiceContract {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUser(@PathVariable("id") Long id);
}
