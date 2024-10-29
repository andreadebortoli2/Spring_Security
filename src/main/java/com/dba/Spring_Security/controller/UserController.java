package com.dba.Spring_Security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dba.Spring_Security.model.User;
import com.dba.Spring_Security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return service.savUser(user);
    }

}
