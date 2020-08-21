package com.ts.clerk.gateway.excption.handler.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.clerk.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

@Component
public class SecurityErrorHandler {
    @Autowired
    ObjectMapper objectMapper;

    public Mono<Void> handle(ServerWebExchange exchange, String message, HttpStatus status) {

        return Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(status);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    ApiResponse apiResponse = ApiResponse.error(status.value(), message);
                    byte[] bytes;
                    try {
                        bytes = objectMapper.writeValueAsString(apiResponse).getBytes(
                                Charset.defaultCharset());
                    } catch (JsonProcessingException e) {
                        bytes = e.getMessage().getBytes(Charset.defaultCharset());
                    }
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    DataBuffer buffer = dataBufferFactory.wrap(bytes);
                    return response.writeWith(Mono.just(buffer))
                            .doOnError(error -> DataBufferUtils.release(buffer));
                });
    }
}
