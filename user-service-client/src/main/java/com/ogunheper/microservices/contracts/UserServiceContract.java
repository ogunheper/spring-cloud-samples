package com.ogunheper.microservices.contracts;

import com.ogunheper.microservices.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UserServiceContract {

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    Long createUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUser(@PathVariable("id") Long id);
}
