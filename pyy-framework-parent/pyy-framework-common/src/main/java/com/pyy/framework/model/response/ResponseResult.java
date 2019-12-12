package com.pyy.framework.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // 空数据不显示
public class ResponseResult<T> implements Response {

    //操作是否成功
    boolean success = SUCCESS;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String message;

    T data;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ResponseResult(ResultCode resultCode, T data){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public ResponseResult(ResultCode resultCode, String message){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = message;
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static <T> ResponseResult SUCCESS(T data){
        return new ResponseResult(CommonCode.SUCCESS, data);
    }

    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }

}
