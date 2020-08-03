package com.wind.clerk.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControl {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
