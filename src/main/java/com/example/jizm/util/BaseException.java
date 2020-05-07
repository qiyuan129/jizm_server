package com.example.jizm.util;

public class BaseException extends RuntimeException {
    int errorCode;
    public BaseException(int code,String message){
        super(message);
        this.errorCode=code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
