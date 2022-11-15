package com.kocesat.project.springjwt.base.model;

public class BaseResponse {
    private String message;
    private Object data;

    private BaseResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    private BaseResponse(Object data) {
        this.data = data;
    }

    public static BaseResponse success(Object data) {
        return new BaseResponse(data);
    }

    public static BaseResponse fail(String message, Object data) {
        return new BaseResponse(message, data);
    }
}
