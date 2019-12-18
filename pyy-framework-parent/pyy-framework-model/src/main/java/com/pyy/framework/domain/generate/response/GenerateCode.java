package com.pyy.framework.domain.generate.response;

import com.pyy.framework.model.response.ResultCode;
import lombok.ToString;

/**
 * CMS响应码
 */
@ToString
public enum GenerateCode implements ResultCode {
    GENERATE_ADDDATASOURCE_EXISTSNAME(false,33001,"数据源已存在！"),
    GENERATE_DATASOURCE_NOT_FOUND(false,33002,"数据源数据未找到！"),
    GENERATE_QUERY_SQL_ISNULL(false,33003,"输入的sql查询语句为空！！"),
    GENERATE_OPEN_CONNECTION_EXCEPTION(false,33004,"连接创建失败！"),
    GENERATE_CLOSE_CONNECTION_EXCEPTION(false,33005,"连接销毁失败！"),
    GENERATE_CONNECTION_EXCEPTION(false,33006,"连接失败！"),
    GENERATE_GET_QUERY_SQL_EXCEPTION(false,33007,"获取sql查询出错！"),;
    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private GenerateCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
