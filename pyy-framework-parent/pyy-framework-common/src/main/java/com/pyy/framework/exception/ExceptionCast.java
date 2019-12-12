package com.pyy.framework.exception;

import com.pyy.framework.model.response.ResultCode;

/**
 * ========================
 * 异常抛出类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/12 15:15
 * Version: v1.0
 * ========================
 */
public class ExceptionCast {

    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
