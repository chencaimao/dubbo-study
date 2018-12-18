package com.ccm.dubbobeans.enums;

/**
 * Created by chencm on 2018/12/4
 */
public enum ResultEnum {
    SUCCESS("0","成功"),
    ERROR_UNKNOWN("101","未知异常");

    private String code;
    private String message;

    ResultEnum(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return this.code;
    }
}
