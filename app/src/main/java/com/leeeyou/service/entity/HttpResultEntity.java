package com.leeeyou.service.entity;

public class HttpResultEntity<T> extends BaseEntity<T> {
    public boolean isSuccess() {
        return getErrorCode() == 0;
    }
}
