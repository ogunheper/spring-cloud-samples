package com.ogunheper.microservices.controllers;

import com.ogunheper.microservices.contracts.UserServiceContract;
import com.ogunheper.microservices.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserServiceContract {

    @Override
    public Long createUser(@RequestBody User user) {
        return 999L;
    }

    @Override
    public User getUser(@PathVariable("id") Long id) {
        return User.builder().id(id).name("Ogün").surname("Heper").build();
    }
}
