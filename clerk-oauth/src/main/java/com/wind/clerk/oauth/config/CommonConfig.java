package com.wind.clerk.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CommonConfig {
    /**
     * 为了解决循环依赖
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // DelegatingPasswordEncoder 委托模式，+策略模式，可以动态决定 passwordEncode
        return new BCryptPasswordEncoder();
    }
}
