package com.ts.clerk.common.exception;

public enum BizEnum {
    USER_PASSWORD_INCONSISTENCY(6001, "password inconsistency"),
    USER_NOT_EXIST(6002, "user not exist");
    private Integer code;
    private String message;

    BizEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
