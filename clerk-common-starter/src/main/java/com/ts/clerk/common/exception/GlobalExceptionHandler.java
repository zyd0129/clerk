package com.ts.clerk.common.exception;

import com.ts.clerk.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleIOException(IOException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(BizException.BizAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handle(BizException.BizAuthenticationException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public ApiResponse handle(BizException e) {
        log.error(e.getMessage(), e);
        return ApiResponse.error(e.getCode(), e.getMessage());
    }

}
