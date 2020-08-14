package com.wind.clerk.gateway.excption.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    public JsonErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                        ResourceProperties resourceProperties,
                                        ErrorProperties errorProperties,
                                        ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        ErrorAttributeOptions errorAttributeOptions = getErrorAttributeOptions(request, MediaType.ALL);
        Map<String, Object> error = getErrorAttributes(request, errorAttributeOptions);
        Map<String, Object> customError = new LinkedHashMap<>();
        customError.put("code", getHttpStatus(error));
        customError.put("message", error.get("message"));
        customError.put("success", false);
        if (errorAttributeOptions.isIncluded(ErrorAttributeOptions.Include.EXCEPTION)) {
            customError.put("exception", customError.getOrDefault("exception", ""));
        }
        if (errorAttributeOptions.isIncluded(ErrorAttributeOptions.Include.STACK_TRACE)) {
            customError.put("trace", customError.getOrDefault("trace", ""));
        }

        return ServerResponse.status(getHttpStatus(error)).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customError));
    }
}