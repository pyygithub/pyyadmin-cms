package com.pyy.framework.domain.cms.response;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.framework.model.response.ResultCode;
import lombok.Data;

/**
 * CMS页面响应结果
 */
@Data
public class CmsPageResult extends ResponseResult {

    CmsPage cmsPage;

    public CmsPageResult(ResultCode resultCode,CmsPage cmsPage) {
        super(resultCode);
        this.cmsPage = cmsPage;
    }
}
