package com.wind.clerk.gateway.excption.handler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public final class ClerkServerAuthenticationEntryPoint implements
        ServerAuthenticationEntryPoint {

    @Autowired
    SecurityErrorHandler errorHandler;
    private HttpStatus status = HttpStatus.UNAUTHORIZED;

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException authException) {
        return errorHandler.handle(exchange, authException.getMessage(), status);
    }
}
