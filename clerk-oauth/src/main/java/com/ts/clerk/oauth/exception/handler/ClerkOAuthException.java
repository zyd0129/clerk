package com.ts.clerk.oauth.exception.handler;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@JsonSerialize(using = ClerkOAuthExceptionJacksonSerializer.class)
public class ClerkOAuthException extends OAuth2Exception {

    private OAuth2Exception oAuth2Exception;

    public ClerkOAuthException(String msg, OAuth2Exception t) {
        super(msg, t);
        oAuth2Exception = t;
    }

    public ClerkOAuthException(String msg) {
        super(msg);
    }

    @Override
    public int getHttpErrorCode() {
        return oAuth2Exception.getHttpErrorCode();
    }
}