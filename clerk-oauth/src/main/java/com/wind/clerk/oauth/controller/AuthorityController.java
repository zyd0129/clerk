package com.wind.clerk.oauth.controller;

import com.wind.clerk.oauth.dao.AuthorityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test/authorities")
@RestController
public class AuthorityController {
    @Autowired
    AuthorityMapper authorityMapper;

    public void addAuthority() {

    }
}
