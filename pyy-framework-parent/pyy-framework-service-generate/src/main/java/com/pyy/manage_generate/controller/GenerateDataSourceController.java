package com.pyy.manage_generate.controller;

import com.pyy.api.generate.GenerateDataSourceControllerApi;
import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.request.QueryDataSourceRequest;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_generate.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * 代码自动生成数据源管理Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class GenerateDataSourceController implements GenerateDataSourceControllerApi {

    @Autowired
    private DataSourceService dataSourceService;

    @Override
    public ResponseResult<List<DataSource>> findList(QueryDataSourceRequest queryDataSourceRequest) {
        return ResponseResult.SUCCESS(dataSourceService.findList(queryDataSourceRequest));
    }

    @Override
    public ResponseResult<QueryResult<DataSource>> findList(int page, int size, QueryDataSourceRequest queryDataSourceRequest) {
        return ResponseResult.SUCCESS(dataSourceService.findList(page, size, queryDataSourceRequest));
    }

    @Override
    public ResponseResult<DataSource> add(DataSource dataSource) {
        return ResponseResult.SUCCESS(dataSourceService.add(dataSource));
    }

    @Override
    public ResponseResult<DataSource> findById(String id) {
        return ResponseResult.SUCCESS(dataSourceService.findById(id));
    }

    @Override
    public ResponseResult<DataSource> edit(String id, DataSource dataSource) {
        return ResponseResult.SUCCESS(dataSourceService.update(id, dataSource));
    }

    @Override
    public ResponseResult delete(String id) {
        dataSourceService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult testConnection(@Valid DataSource dataSource) {
        dataSourceService.testConnection(dataSource);
        return ResponseResult.SUCCESS();
    }
}
