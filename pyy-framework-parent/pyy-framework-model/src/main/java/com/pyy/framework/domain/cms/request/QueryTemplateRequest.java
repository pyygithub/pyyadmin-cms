package com.pyy.framework.domain.cms.request;

import com.pyy.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 模板查询条件
 */
@Data
public class QueryTemplateRequest extends RequestData {

    @ApiModelProperty("模板名称")
    private String templateName;
}
