package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsConfigControllerApi;
import com.pyy.api.cms.CmsSiteControllerApi;
import com.pyy.framework.domain.cms.CmsConfig;
import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.service.CmsConfigService;
import com.pyy.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * CMS配置管理Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private CmsConfigService cmsConfigService;

    @Override
    public CmsConfig getModel(String id) {
        return cmsConfigService.getConfigById(id);
    }
}
