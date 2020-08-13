package com.wind.clerk.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.wind.clerk.common.context.UserToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        String appIdStr = exchange.getRequest().getHeaders().getFirst("appId");
        if (StringUtils.isEmpty(token)) {
            return chain.filter(exchange);
        }
        try {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("Bearer ", "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            String userStr = jwsObject.getPayload().toString();
            UserToken userToken = JSONObject.parseObject(userStr, UserToken.class);
            if (!StringUtils.isEmpty(appIdStr)) {
                userToken.setAppId(Integer.valueOf(appIdStr));
            }
            ServerHttpRequest request = exchange.getRequest().mutate().headers(httpHeaders -> {
                httpHeaders.add("userToken", JSONObject.toJSONString(userToken));
                httpHeaders.remove("appId");
                httpHeaders.remove("Authorization");
            }).build();
            exchange = exchange.mutate().request(request).build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}