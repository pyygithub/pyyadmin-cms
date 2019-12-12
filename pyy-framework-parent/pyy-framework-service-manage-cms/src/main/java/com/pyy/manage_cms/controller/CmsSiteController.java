package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsSiteControllerApi;
import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResponseResult;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ========================
 * 站点Controller
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 15:33
 * Version: v1.0
 * ========================
 */
@RestController
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    public QueryResponseResult findList(int page, int size, QuerySiteRequest querySiteRequest) {
        QueryResult queryResult = cmsSiteService.findList(page, size, querySiteRequest);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public ResponseResult<CmsSite> add(CmsSite cmsSite) {
        cmsSite = cmsSiteService.add(cmsSite);
        return ResponseResult.SUCCESS(cmsSite);
    }

    @Override
    public ResponseResult<CmsSite> findById(String id) {
        CmsSite cmsSite = cmsSiteService.findById(id);
        return ResponseResult.SUCCESS(cmsSite);
    }

    @Override
    public ResponseResult<CmsSite> edit(String id, CmsSite cmsSite) {
        cmsSite = cmsSiteService.update(id, cmsSite);
        return ResponseResult.SUCCESS(cmsSite);
    }

    @Override
    public ResponseResult delete(String id) {
        cmsSiteService.delete(id);
        return ResponseResult.SUCCESS();
    }
}
