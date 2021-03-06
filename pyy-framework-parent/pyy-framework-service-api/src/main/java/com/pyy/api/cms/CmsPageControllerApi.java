package com.pyy.api.cms;

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
 * ---------------------------
 * CMS页面查询接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@Api(value="cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
@RequestMapping("/cms")
public interface CmsPageControllerApi {

    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    @GetMapping("/pages/{page}/{size}")
    ResponseResult<QueryResult<CmsPage>> findList(@Valid @PathVariable("page")int page, @PathVariable("size")int size, QueryPageRequest queryPageRequest) ;

    @ApiOperation("添加页面")
    @PostMapping("/page")
    ResponseResult<CmsPage> add(@Valid @RequestBody CmsPage cmsPage);

    @ApiOperation("根据id查询页面")
    @GetMapping("/page/{id}")
    ResponseResult<CmsPage> findById(@Valid @PathVariable("id") String id);

    @ApiOperation("编辑页面")
    @PutMapping("/page/{id}")
    ResponseResult<CmsPage> edit(@Valid @PathVariable("id") String id, @RequestBody CmsPage cmsPage);

    @ApiOperation("删除页面")
    @DeleteMapping("/page/{id}")
    ResponseResult delete(@Valid @PathVariable("id") String id);

    @ApiOperation("发布页面")
    @PostMapping("/page/postPage/{pageId}")
    ResponseResult postPage(@PathVariable("pageId") String pageId);
}
