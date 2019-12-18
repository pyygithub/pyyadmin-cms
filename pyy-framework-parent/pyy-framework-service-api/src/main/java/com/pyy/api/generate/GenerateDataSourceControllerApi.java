package com.pyy.api.generate;

import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.request.QueryDataSourceRequest;
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
 * 代码自动生成 数据源管理接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 14:57
 * Version: v1.0
 * ========================
 */
@Api(value="代码自动生成 数据源管理接口",description = "代码自动生成 数据源管理接口，提供数据源的管理、查询接口")
@RequestMapping("/generate")
public interface GenerateDataSourceControllerApi {
    @ApiOperation("查询数据源列表")
    @GetMapping("/dataSources")
    ResponseResult<List<DataSource>> findList(@Valid QueryDataSourceRequest queryDataSourceRequest) ;

    @ApiOperation("分页查询数据源列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="path", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="path", dataType="int")
    })
    @GetMapping("/dataSources/{page}/{size}")
    ResponseResult<QueryResult<DataSource>> findList(@Valid @PathVariable("page") int page, @PathVariable("size") int size, QueryDataSourceRequest queryDataSourceRequest) ;

    @ApiOperation("添加数据源")
    @PostMapping("/dataSource")
    ResponseResult<DataSource> add(@Valid @RequestBody DataSource dataSource);

    @ApiOperation("根据id查询数据源")
    @GetMapping("/dataSource/{id}")
    ResponseResult<DataSource> findById(@Valid @PathVariable("id") String id);

    @ApiOperation("编辑数据源")
    @PutMapping("/dataSource/{id}")
    ResponseResult<DataSource> edit(@Valid @PathVariable("id") String id, @RequestBody DataSource dataSource);

    @ApiOperation("删除数据源")
    @DeleteMapping("/dataSource/{id}")
    ResponseResult delete(@Valid @PathVariable("id") String id);

    @ApiOperation("删除数据源")
    @PostMapping("/dataSource/testConnection")
    ResponseResult testConnection(@Valid @RequestBody DataSource dataSource);
}
