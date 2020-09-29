package com.ts.clerk.common.exception;

/**
 * BizEnum
 */
public enum BizEnum {
    /**
     * BizEnum
     */
    ERROR(-1, "操作失败"),
    SUCCESS(0, "操作成功"),
    USER_PASSWORD_INCONSISTENCY(6001, "password inconsistency"),
    USER_NOT_EXIST(6002, "user not exist"),

    //---------------------------JURY-------------------------------------------//
    JURY_ERROR(9000, "jury unknown error"),
    JURY_FAILED(9001, "jury response failed"),
    JURY_VALIDATE_FAILED(9002, "jury parameter verification failed");

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
