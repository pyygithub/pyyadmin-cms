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
