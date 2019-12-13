package com.pyy.api.cms;

import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * ========================
 * Cms站点查询接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:57
 * Version: v1.0
 * ========================
 */
@Api(value="cms站点管理接口",description = "cms站点管理接口，提供站点的增、删、改、查")
@RequestMapping("/cms")
public interface CmsSiteControllerApi {

    @ApiOperation("查询站点列表")
    @GetMapping("/sites")
    ResponseResult<List<CmsSite>> findList(@Valid QuerySiteRequest querySiteRequest) ;

    @ApiOperation("分页查询站点列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="site",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    @GetMapping("/sites/{page}/{size}")
    ResponseResult<QueryResult<CmsSite>> findList(@Valid @PathVariable("page") int page, @PathVariable("size") int size, QuerySiteRequest querySiteRequest) ;

    @ApiOperation("添加站点")
    @PostMapping("/site")
    ResponseResult<CmsSite> add(@Valid @RequestBody CmsSite cmsSite);

    @ApiOperation("根据id查询站点")
    @GetMapping("/site/{id}")
    ResponseResult<CmsSite> findById(@Valid @PathVariable("id") String id);

    @ApiOperation("编辑站点")
    @PutMapping("/site/{id}")
    ResponseResult<CmsSite> edit(@Valid @PathVariable("id") String id, @RequestBody CmsSite cmsSite);

    @ApiOperation("删除站点")
    @DeleteMapping("/site/{id}")
    ResponseResult delete(@Valid @PathVariable("id") String id);
}
