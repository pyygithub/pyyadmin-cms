package com.pyy.framework.exception;

import com.pyy.framework.model.response.ResultCode;

/**
 * ========================
 * 自定义异常类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/12 15:13
 * Version: v1.0
 * ========================
 */
public class CustomException extends RuntimeException{

    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        //异常信息为错误代码+异常信息
        super("错误代码："+resultCode.code()+"错误信息："+resultCode.message());
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return this.resultCode;
    }
}
