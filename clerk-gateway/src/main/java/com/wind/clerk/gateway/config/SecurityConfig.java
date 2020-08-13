package com.wind.clerk.gateway.config;

import com.wind.clerk.gateway.filter.CorsFilter;
import com.wind.clerk.gateway.handler.ClerkServerAccessDeniedHandler;
import com.wind.clerk.gateway.handler.ClerkServerAuthenticationEntryPoint;
import com.wind.clerk.gateway.manager.ClerkReactiveAuthorizationManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
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
    private ClerkServerAuthenticationEntryPoint clerkServerAuthenticationEntryPoint;
    private ClerkServerAccessDeniedHandler clerkServerAccessDeniedHandler;

    /**
     * 认证最重要的是authenticationWebFilter，和ReactiveAuthenticationManager，这里使用默认的。自定义如下
     * //token管理器-jwt实现类
     * ReactiveAuthenticationManager tokenAuthenticationManager =
     * new JwtAuthenticationManager(new JwtTokenStore(accessTokenConverter()));
     * //认证过滤器
     * AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(tokenAuthenticationManager);
     * authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
     * 授权最重要的是ReactiveAuthorizationManager，这里使用自定义的，根据url授权
     */
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.cors().and().csrf().disable().authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers(securityProperties.getNotRequireAuthentication()).permitAll()
                .pathMatchers(securityProperties.getNotRequireAuthorization()).authenticated()
                .anyExchange().access(clerkReactiveAuthorizationManager)
                .and().exceptionHandling().accessDeniedHandler(clerkServerAccessDeniedHandler).authenticationEntryPoint(clerkServerAuthenticationEntryPoint)
        ;


        //这里提供一个JwtAuthenticationConverter扩展点，这里是默认实现
        ReactiveJwtAuthenticationConverterAdapter reactiveJwtAuthenticationConverterAdapter = new ReactiveJwtAuthenticationConverterAdapter(new JwtAuthenticationConverter());
        http.oauth2ResourceServer()
                .authenticationEntryPoint(clerkServerAuthenticationEntryPoint)
                .accessDeniedHandler(clerkServerAccessDeniedHandler)
                .jwt().jwtAuthenticationConverter(reactiveJwtAuthenticationConverterAdapter);
        http.addFilterAt(new CorsFilter(), SecurityWebFiltersOrder.CORS);

        return http.build();
    }
}
