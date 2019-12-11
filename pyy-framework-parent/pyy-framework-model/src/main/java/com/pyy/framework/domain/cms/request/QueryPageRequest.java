package com.pyy.framework.domain.cms.request;

import com.pyy.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ========================
 * 页面查询条件
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:54
 * Version: v1.0
 * ========================
 */
@Data
public class QueryPageRequest extends RequestData {

    @ApiModelProperty("站点id")
    private String siteId;

    @ApiModelProperty("页面ID")
    private String pageId;

    @ApiModelProperty("页面名称")
    private String pageName;

    @ApiModelProperty("页面别名")
    private String pageAliase;

    @ApiModelProperty("模版id")
    private String templateId;
}
