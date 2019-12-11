package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsPageControllerApi;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.domain.cms.response.CmsPageResult;
import com.pyy.framework.model.response.QueryResponseResult;
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
@RequestMapping("/cms")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/page/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(page,size,queryPageRequest);
    }

    @Override
    @PostMapping("/page")
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }
}
