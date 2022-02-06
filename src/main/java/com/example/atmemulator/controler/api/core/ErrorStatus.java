package com.example.atmemulator.controler.api.core;

public enum ErrorStatus {
    SUCCESS(0,"SUCCESS"),
    NOT_FOUND(404,"NOT_FOUND"),
    UNKNOWN_ERROR(1,"UNKNOWN_ERROR");

    private int code;
    private String message;

    ErrorStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
