package com.wind.clerk.oauth.controller;

import com.wind.clerk.oauth.service.UserService;
import com.wind.clerk.oauth.service.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("test")
@RestController
public class TestControl {

    @Autowired
    UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("users")
    public List<UserBO> userBOList() {
        return userService.query();
    }
}
