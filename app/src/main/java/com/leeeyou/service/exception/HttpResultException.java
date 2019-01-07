package com.leeeyou.service.exception;

import java.io.IOException;

public class HttpResultException extends IOException {
    private int code;
    private String msg;

    public HttpResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
