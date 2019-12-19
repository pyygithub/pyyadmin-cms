package com.pyy.framework.domain.generate.request;

import com.pyy.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ========================
 * 数据源查询条件
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:54
 * Version: v1.0
 * ========================
 */
@Data
public class QueryDataSourceRequest extends RequestData {

    @ApiModelProperty("数据源名称")
    private String name;

    @ApiModelProperty("数据库用户名")
    private String username;

    @ApiModelProperty("数据库名称")
    private String dbName;
}
