package com.pyy.api.cms;

import com.pyy.framework.domain.cms.CmsTemplate;
import com.pyy.framework.domain.cms.request.QueryTemplateRequest;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * CMS页面模板查询接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-10 11:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@Api(value="CMS页面模板管理接口",description = "CMS页面模板管理接口，提供页面模板的增、删、改、查")
@RequestMapping("/cms")
public interface CmsTemplateControllerApi {

    @ApiOperation("查询页面模板列表")
    @GetMapping("/templates")
    ResponseResult<List<CmsTemplate>> findList(@Valid QueryTemplateRequest queryTemplateRequest) ;

    @ApiOperation("分页查询页面模板列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    @GetMapping("/templates/{page}/{size}")
    ResponseResult<QueryResult<CmsTemplate>> findList(@Valid @PathVariable("page") int page, @PathVariable("size") int size, QueryTemplateRequest queryTemplateRequest) ;

    @ApiOperation("添加页面模板")
    @PostMapping("/template")
    ResponseResult<CmsTemplate> add(@Valid @RequestBody CmsTemplate cmsTemplate);

    @ApiOperation("根据id查询页面模板")
    @GetMapping("/template/{id}")
    ResponseResult<CmsTemplate> findById(@Valid @PathVariable("id") String id);

    @ApiOperation("编辑页面模板")
    @PutMapping("/template/{id}")
    ResponseResult<CmsTemplate> edit(@Valid @PathVariable("id") String id, @RequestBody CmsTemplate cmsTemplate);

    @ApiOperation("删除页面模板")
    @DeleteMapping("/template/{id}")
    ResponseResult delete(@Valid @PathVariable("id") String id);

    @ApiOperation("页面模板文件删除")
    @PostMapping("/template/upload")
    ResponseResult upload(@RequestParam("file") MultipartFile file);
}
