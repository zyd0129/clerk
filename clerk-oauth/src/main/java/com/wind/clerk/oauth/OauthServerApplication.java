package com.wind.clerk.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@MapperScan("com.wind.clerk.oauth.dao.mapper")
public class OauthServerApplication implements CommandLineRunner {
//    @Autowired
//    KeyProperties keyProperties;
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(keyProperties.getKeyStore());
    }
}
