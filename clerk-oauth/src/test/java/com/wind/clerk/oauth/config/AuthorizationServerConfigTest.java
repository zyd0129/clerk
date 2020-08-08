package com.wind.clerk.oauth.config;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.jwt.JwtHelper;

import static org.junit.Assert.*;

//@SpringBootTest
public class AuthorizationServerConfigTest {

//    @Autowired
//    KeyProperties keyProperties;

    @Test
    public void main() {
        String accessToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJhdWQiOlsicmVzMSJdLCJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHA" +
                "iOjE1OTY4NDExMTgsInVzZXJJZCI6MSwiYXV0aG9yaXRpZXMiOlsicnVsZV9hZGQiLCJydWxlX2xpc3QiXSwi" +
                "anRpIjoiNDYwYTk0YWMtNDBmYy00YzYxLWFkYjItMzIwYWEzZjAyNjBmIiwiY2xpZW50X2lkIjoiYzEifQ.OD_0rSMGi_" +
                "ErEAeJBgnxdYkVPkwsMRAG7bwx9t93Ncs8zeRAyGuDy_WZDHmcnKPJBoA44iWe0Rdw8MEzYMpZdtZ9A6CL9X8JAMWb3F5XZ" +
                "OEUhKWdrE-XcZoPXAmLhFcna26RBPmic81DSD0CkezGYzp5YIQ4zQmkhybtxKHkSlsYfqSKZ8nVXIyXoJK3SOO_UWG0YAs5Ybx5jYk" +
                "EQR4pV10eqITN8dQt9wct3AJ0V8uydSnJkB2GbfF4g_X57GKxnO3tJE8pS9GP6zzHssNIko9jGi8Z2DRGhOeSnMC4jfL57hKTzpM" +
                "ArIyXKWiIDL4f3PvsLvPklFPHnR1idAvJkQ";
        System.out.println(JwtHelper.decode(accessToken));
    }
}