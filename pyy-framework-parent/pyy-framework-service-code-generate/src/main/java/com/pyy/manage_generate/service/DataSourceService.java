package com.pyy.manage_generate.service;

import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.request.QueryDataSourceRequest;
import com.pyy.framework.model.response.QueryResult;

import java.util.List;

public interface DataSourceService {

    /**
     * 根据ID查询数据源
     * @param id
     * @return
     */
    DataSource findById(String id);

    /**
     * 数据源列表查询
     * @param queryDataSourceRequest
     * @return
     */
    List<DataSource> findList(QueryDataSourceRequest queryDataSourceRequest) ;

    /**
     * 数据源列表分页查询
     * @param page
     * @param size
     * @param queryDataSourceRequest
     * @return
     */
    QueryResult<DataSource> findList(int page, int size, QueryDataSourceRequest queryDataSourceRequest) ;

    /**
     * 添加数据源
     * @param DataSource
     * @return
     */
    DataSource add(DataSource DataSource);

    /**
     * 修改数据源
     * @param id
     * @param DataSource
     * @return
     */
    DataSource update(String id, DataSource DataSource);

    /**
     * 根据ID删除数据源
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 测试数据库连接
     * @param dataSource
     */
    void testConnection(DataSource dataSource);
}
