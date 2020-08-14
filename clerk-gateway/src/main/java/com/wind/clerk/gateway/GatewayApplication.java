package com.wind.clerk.gateway;

import com.wind.clerk.gateway.excption.handler.ExtensionErrorAttributes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }


    /**
     * 自定义 ExtensionErrorAttributes Bean注册
     */
//    @Bean
    public ExtensionErrorAttributes errorAttributes() {
        return new ExtensionErrorAttributes(false);
    }

}
