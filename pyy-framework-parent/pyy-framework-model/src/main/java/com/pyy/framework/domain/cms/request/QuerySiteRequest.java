package com.pyy.framework.domain.cms.request;

import com.pyy.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ========================
 * 站点查询条件
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:54
 * Version: v1.0
 * ========================
 */
@Data
public class QuerySiteRequest extends RequestData {

    @ApiModelProperty("站点名称")
    private String siteName;
}
