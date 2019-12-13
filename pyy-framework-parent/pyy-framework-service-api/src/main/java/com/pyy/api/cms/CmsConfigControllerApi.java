package com.pyy.api.cms;

import com.pyy.framework.domain.cms.CmsConfig;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ========================
 * cms配置管理接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:57
 * Version: v1.0
 * ========================
 */

@Api(value="cms配置管理接口",description = "cms配置管理接口，提供数据模型的管理、查询接口")
@RequestMapping("/cms")
public interface CmsConfigControllerApi {

    @ApiOperation("根据id查询CMS配置信息")
    @GetMapping("/config/model/{id}")
    CmsConfig getModel(@PathVariable("id") String id);
}
