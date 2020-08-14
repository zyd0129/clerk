package com.wind.clerk.gateway.manager;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * accessManger权限即资源路径
 */
@Component
public class ClerkReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    private AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        String url = object.getExchange().getRequest().getPath().value();
        return authentication
                .filter(a -> a.isAuthenticated())
                .flatMapIterable(a -> {
                    Jwt jwt = (Jwt) a.getPrincipal();
                    return (List<String>) jwt.getClaims().get("authorities");
                })
                .any(u -> matcher.match(u, url))
                .map(hasAuthority -> new AuthorizationDecision(hasAuthority))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
