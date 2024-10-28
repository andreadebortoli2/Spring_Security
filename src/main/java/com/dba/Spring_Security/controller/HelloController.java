package com.dba.Spring_Security.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String greet(HttpServletRequest req) {
        return "Hello World! " + req.getSession().getId();
    }

}
