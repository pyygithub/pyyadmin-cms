package com.pyy.manage_generate.service;

import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.GenerateModel;
import com.pyy.framework.domain.generate.Table;

import java.util.List;

public interface GenerateService {
    /**
     * 是否可以连接
     *
     * @param dataSource
     * @return
     */
    boolean testConnection(DataSource dataSource);

    /**
     * 反向查找数据表
     * @param dataSource
     * @return
     */
    List<Table> getTables(DataSource dataSource);

    /**
     * 获取代码生成数据模型
     *
     * @param generateModel
     * @return
     */
    GenerateModel getGenerateModel(GenerateModel generateModel);

    /**
     * 生成代码文件
     *
     * @param generateModel
     * @return
     */
    boolean generateModels(GenerateModel generateModel);
}
