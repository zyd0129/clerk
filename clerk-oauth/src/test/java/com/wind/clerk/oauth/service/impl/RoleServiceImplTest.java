package com.wind.clerk.oauth.service.impl;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RoleServiceImplTest {

    @Test
    public void test() {
//        Set<String> stringSet = new HashSet<>(null);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String admin = encoder.encode("admin");
        System.out.println(encoder.matches("admin",admin));
    }

}