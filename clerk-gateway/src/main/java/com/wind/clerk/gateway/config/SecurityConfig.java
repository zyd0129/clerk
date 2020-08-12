package com.wind.clerk.gateway.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@AllArgsConstructor
public class SecurityConfig {

    private SecurityProperties securityProperties;
    private ClerkReactiveAuthorizationManager clerkReactiveAuthorizationManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(securityProperties.getNotRequireAuthentication()).permitAll()
                .pathMatchers(securityProperties.getNotRequireAuthorization()).authenticated()
                .anyExchange().access(clerkReactiveAuthorizationManager);

        ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverterAdapter = new ReactiveJwtAuthenticationConverterAdapter(new JwtAuthenticationConverter());
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(reactiveJwtAuthenticationConverterAdapter);
        return http.build();
    }
}
