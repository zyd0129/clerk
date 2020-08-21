package com.ts.clerk.gateway.excption.handler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ClerkServerAccessDeniedHandler implements ServerAccessDeniedHandler {
    @Autowired
    SecurityErrorHandler errorHandler;
    private HttpStatus status = HttpStatus.FORBIDDEN;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {

        return errorHandler.handle(exchange, denied.getMessage(), status);
    }
}
