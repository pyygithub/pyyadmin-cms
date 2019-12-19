package com.pyy.manage_generate.service.impl;

import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.request.QueryDataSourceRequest;
import com.pyy.framework.domain.generate.response.GenerateCode;
import com.pyy.framework.exception.ExceptionCast;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.manage_generate.dao.DataSourceRepository;
import com.pyy.manage_generate.service.DataSourceService;
import com.pyy.manage_generate.service.DatabaseService;
import com.pyy.manage_generate.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceRepository dataSourceRepository;

    @Autowired
    private DatabaseService databaseService;
    @Override
    public DataSource add(DataSource dataSource) {
        // 校验数据源是否存在，根据数据库类型、用户名、数据库名称查询
        DataSource oldDataSource = dataSourceRepository.findByDbTypeAndUsernameAndDbName(dataSource.getDbType(), dataSource.getUsername(), dataSource.getDbName());
        // 已存在则抛出异常
        if (oldDataSource != null) {
            ExceptionCast.cast(GenerateCode.GENERATE_ADDDATASOURCE_EXISTSNAME);
        }
        dataSource.setId(null);//添加站点主键由spring data 自动生成
        dataSource.setCreateId(UserUtil.getUserId());
        dataSource.setCreateName(UserUtil.getUsername());
        dataSource.setCreateTime(new Date());
        dataSource.setUpdateId(UserUtil.getUserId());
        dataSource.setUpdateName(UserUtil.getUsername());
        dataSource.setUpdateTime(new Date());
        dataSourceRepository.save(dataSource);
        // 返回结果
        return dataSource;
    }

    @Override
    public DataSource update(String id, DataSource dataSource) {
        // 根据ID查询数据源信息
        DataSource one = this.findById(id);
        if (one == null) {
            // 数据源数据未找到，抛出异常
            ExceptionCast.cast(GenerateCode.GENERATE_DATASOURCE_NOT_FOUND);
        }
        one.setName(dataSource.getName());
        one.setDbType(dataSource.getDbType());
        one.setHost(dataSource.getHost());
        one.setPort(dataSource.getPort());
        one.setUsername(dataSource.getUsername());
        one.setPassword(dataSource.getPassword());
        one.setDbName(dataSource.getDbName());
        one.setUpdateId(UserUtil.getUserId());
        one.setUpdateName(UserUtil.getUsername());
        one.setUpdateTime(new Date());
        // 校验数据源是否存在，根据数据库类型、用户名、数据库名称查询
        DataSource oldDataSource = dataSourceRepository.findByDbTypeAndUsernameAndDbName(dataSource.getDbType(), dataSource.getUsername(), dataSource.getDbName());
        // 已存在且不是本条数据则抛出异常
        if (oldDataSource != null && !oldDataSource.getId().equals(id)) {
            ExceptionCast.cast(GenerateCode.GENERATE_ADDDATASOURCE_EXISTSNAME);
        }
        // 执行更新
        DataSource save = dataSourceRepository.save(one);
        if (save == null) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 返回更新后对象
        return save;
    }

    @Override
    public void delete(String id) {
        Optional<DataSource> optional = dataSourceRepository.findById(id);
        if (!optional.isPresent()) {
            // 数据源数据未找到，抛出异常
            ExceptionCast.cast(GenerateCode.GENERATE_DATASOURCE_NOT_FOUND);
        }
        dataSourceRepository.deleteById(id);
    }

    @Override
    public DataSource findById(String id) {
        Optional<DataSource> optional = dataSourceRepository.findById(id);
        if (optional.isPresent()) {
            DataSource dataSource = optional.get();
            return dataSource;
        }
        return null;
    }

    @Override
    public List<DataSource> findList(QueryDataSourceRequest queryDataSourceRequest) {
        if(queryDataSourceRequest == null){
            queryDataSourceRequest = new QueryDataSourceRequest();
        }
        // 自定义条件查询
        // 定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("dbName", ExampleMatcher.GenericPropertyMatchers.contains());
        // 条件值对象
        DataSource dataSource = new DataSource();
        // 设置条件值
        if(StringUtils.isNotEmpty(queryDataSourceRequest.getName())){
            dataSource.setName(queryDataSourceRequest.getName());
        }
        if(StringUtils.isNotEmpty(queryDataSourceRequest.getUsername())){
            dataSource.setUsername(queryDataSourceRequest.getUsername());
        }
        if(StringUtils.isNotEmpty(queryDataSourceRequest.getDbName())){
            dataSource.setDbName(queryDataSourceRequest.getDbName());
        }
        // 定义条件对象Example
        Example<DataSource> example = Example.of(dataSource, exampleMatcher);
        List<DataSource> all = dataSourceRepository.findAll(example);
        return all;
    }

    @Override
    public QueryResult<DataSource> findList(int page, int size, QueryDataSourceRequest queryDataSourceRequest) {
        if(queryDataSourceRequest == null){
            queryDataSourceRequest = new QueryDataSourceRequest();
        }
        // 自定义条件查询
        // 定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("dbName", ExampleMatcher.GenericPropertyMatchers.contains());
        // 条件值对象
        DataSource dataSource = new DataSource();
        // 设置条件值（数据库用户名）
        if(StringUtils.isNotEmpty(queryDataSourceRequest.getUsername())){
            dataSource.setUsername(queryDataSourceRequest.getUsername());
        }
        if(StringUtils.isNotEmpty(queryDataSourceRequest.getDbName())){
            dataSource.setDbName(queryDataSourceRequest.getDbName());
        }
        // 定义条件对象Example
        Example<DataSource> example = Example.of(dataSource, exampleMatcher);
        // 分页参数
        if(page <=0){
            page = 1;
        }
        page = page - 1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<DataSource> all = dataSourceRepository.findAll(example, pageable);
        QueryResult<DataSource> queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        return queryResult;
    }

    @Override
    public void testConnection(DataSource dataSource) {
        boolean success = databaseService.canConnect(dataSource);
        if (!success) {
            ExceptionCast.cast(GenerateCode.GENERATE_CONNECTION_EXCEPTION);
        }
    }
}
