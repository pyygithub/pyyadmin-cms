package com.pyy.manage_cms.controller;

import com.pyy.api.cms.CmsSiteControllerApi;
import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * CMS站点管理Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-10 15:32:05
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class CmsSiteController implements CmsSiteControllerApi {

    @Autowired
    private CmsSiteService cmsSiteService;

    @Override
    public ResponseResult<List<CmsSite>> findList(@Valid QuerySiteRequest querySiteRequest) {
        return ResponseResult.SUCCESS(cmsSiteService.findList(querySiteRequest));
    }

    @Override
    public ResponseResult<QueryResult<CmsSite>> findList(int page, int size, QuerySiteRequest querySiteRequest) {
        return ResponseResult.SUCCESS(cmsSiteService.findList(page, size, querySiteRequest));
    }

    @Override
    public ResponseResult<CmsSite> add(CmsSite cmsSite) {
        return ResponseResult.SUCCESS(cmsSiteService.add(cmsSite));
    }

    @Override
    public ResponseResult<CmsSite> findById(String id) {
        return ResponseResult.SUCCESS(cmsSiteService.findById(id));
    }

    @Override
    public ResponseResult<CmsSite> edit(String id, CmsSite cmsSite) {
        return ResponseResult.SUCCESS(cmsSiteService.update(id, cmsSite));
    }

    @Override
    public ResponseResult delete(String id) {
        cmsSiteService.delete(id);
        return ResponseResult.SUCCESS();
    }
}
