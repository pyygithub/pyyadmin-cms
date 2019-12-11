package com.pyy.framework.model.response;

import lombok.Data;
import lombok.ToString;

/**
 * 通用查询响应结果
 */
@Data
@ToString
public class QueryResponseResult extends ResponseResult {

    QueryResult queryResult;

    public QueryResponseResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}
