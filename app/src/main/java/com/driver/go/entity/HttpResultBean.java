package com.driver.go.entity;

/**
 * Created by Administrator on 2017/2/22.
 */

public class HttpResultBean<T> {
    private String error_code;
    private String reason;
    private T result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
