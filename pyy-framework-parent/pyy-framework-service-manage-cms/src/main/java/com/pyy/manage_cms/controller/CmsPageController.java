package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsPageControllerApi;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResponseResult;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ========================
 * 页面Controller
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 15:33
 * Version: v1.0
 * ========================
 */
@RestController
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        QueryResult queryResult = cmsPageService.findList(page, size, queryPageRequest);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public ResponseResult<CmsPage> add(CmsPage cmsPage) {
        cmsPage = cmsPageService.add(cmsPage);
        return ResponseResult.SUCCESS(cmsPage);
    }

    @Override
    public ResponseResult<CmsPage> findById(String id) {
        CmsPage cmsPage = cmsPageService.findById(id);
        return ResponseResult.SUCCESS(cmsPage);
    }

    @Override
    public ResponseResult<CmsPage> edit(String id, CmsPage cmsPage) {
        cmsPage = cmsPageService.update(id, cmsPage);
        return ResponseResult.SUCCESS(cmsPage);
    }

    @Override
    public ResponseResult delete(String id) {
        cmsPageService.delete(id);
        return ResponseResult.SUCCESS();
    }
}
