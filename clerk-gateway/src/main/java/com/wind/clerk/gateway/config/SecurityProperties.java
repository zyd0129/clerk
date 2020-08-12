package com.wind.clerk.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("clerk.security")
@Data
public class SecurityProperties {
    private String[] notRequireAuthentication;
    private String[] notRequireAuthorization;
}
