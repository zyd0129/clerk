package com.ts.clerk.common.response;

import com.ts.clerk.common.exception.BizEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 10102
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 7614396417801217389L;
    private static final int SUCCESS = 0;
    private static final int ERROR = -1;
    ApiResponse<T> juryApiResponse;
    /**
     * 返回码 0：成功，其他为错误
     */
    private int code;
    /**
     * 错误消息
     */
    private String message;
    /**
     * 正确时返回的数据
     */
    private T data;

    public static <T> ApiResponse<T> error(BizEnum bizEnum) {
        return ApiResponse.<T>builder()
                .code(bizEnum.getCode())
                .message(bizEnum.getMessage())
                .build();
    }

    public static <T> ApiResponse<T> error(BizEnum bizEnum, String msg) {
        return ApiResponse.<T>builder()
                .code(bizEnum.getCode())
                .message(msg)
                .build();
    }

    public static <T> ApiResponse<T> error(T data) {
        return ApiResponse.<T>builder()
                .code(BizEnum.ERROR.getCode())
                .message(BizEnum.ERROR.getMessage())
                .data(data)
                .build();
    }


    public static <T> ApiResponse<T> error(int resultCode, String msg) {
        return ApiResponse.<T>builder()
                .code(resultCode)
                .message(msg)
                .build();
    }


    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
                .code(SUCCESS)
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .code(SUCCESS)
                .data(data)
                .build();
    }

    public boolean isSuccess() {
        return code == 0;
    }

}