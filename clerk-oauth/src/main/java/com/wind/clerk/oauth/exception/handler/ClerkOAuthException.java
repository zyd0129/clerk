package com.wind.clerk.oauth.exception.handler;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = ClerkOAuthExceptionJacksonSerializer.class)
public class ClerkOAuthException extends OAuth2Exception {

    public ClerkOAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public ClerkOAuthException(String msg) {
        super(msg);
    }
}