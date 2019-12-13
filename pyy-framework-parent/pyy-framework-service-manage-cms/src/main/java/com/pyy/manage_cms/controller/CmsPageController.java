package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsPageControllerApi;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.model.response.CommonCode;
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
    public ResponseResult<QueryResult<CmsPage>> findList(int page, int size, QueryPageRequest queryPageRequest) {
        return ResponseResult.SUCCESS(cmsPageService.findList(page, size, queryPageRequest));
    }

    @Override
    public ResponseResult<CmsPage> add(CmsPage cmsPage) {
        return ResponseResult.SUCCESS(cmsPageService.add(cmsPage));
    }

    @Override
    public ResponseResult<CmsPage> findById(String id) {
        return ResponseResult.SUCCESS(cmsPageService.findById(id));
    }

    @Override
    public ResponseResult<CmsPage> edit(String id, CmsPage cmsPage) {
        return ResponseResult.SUCCESS(cmsPageService.update(id, cmsPage));
    }

    @Override
    public ResponseResult delete(String id) {
        cmsPageService.delete(id);
        return ResponseResult.SUCCESS();
    }
}
