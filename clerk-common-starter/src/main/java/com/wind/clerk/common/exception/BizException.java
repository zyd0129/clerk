package com.wind.clerk.common.exception;

/**
 * 这里使用RuntimeException,抛出时不用捕获，简化开发
 */
public class BizException extends RuntimeException {

    private Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(BizEnum bizEnum) {
        super(bizEnum.getMessage());
        this.code = bizEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public static class BizAuthenticationException extends BizException{

        public BizAuthenticationException(String message) {
            super(401, message);
        }
    }
}
