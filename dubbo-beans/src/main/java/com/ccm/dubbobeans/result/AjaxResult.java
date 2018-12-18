package com.ccm.dubbobeans.result;

import com.ccm.dubbobeans.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by chencm on 2018/12/4
 */
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 2381759816102344786L;

    private T result;

    private String code;

    private String message;

    public AjaxResult(T result){
        this.result=result;
        this.code= ResultEnum.SUCCESS.getCode();
        this.message=ResultEnum.SUCCESS.getMessage();
    }

    public AjaxResult(ResultEnum resultEnum){
        this.code= resultEnum.getCode();
        this.message=resultEnum.getMessage();
    }

    public AjaxResult(T result, ResultEnum resultEnum){
        this.result=result;
        this.code= resultEnum.getCode();
        this.message=resultEnum.getMessage();
    }

    public AjaxResult(String message){
        this.message=message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
