package com.pyy.manage_generate.controller;

import com.pyy.api.generate.GenerateControllerApi;
import com.pyy.framework.domain.generate.DataSource;
import com.pyy.framework.domain.generate.GenerateModel;
import com.pyy.framework.domain.generate.Table;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_generate.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
public class GenerateController implements GenerateControllerApi {

    @Autowired
    private GenerateService generateService;

    @Override
    public ResponseResult testConnection(DataSource dataSource) {
        boolean success = generateService.testConnection(dataSource);
        if (success) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }

    @Override
    public ResponseResult<List<Table>> getTables(DataSource dataSource) {
        return ResponseResult.SUCCESS(generateService.getTables(dataSource));
    }

    @Override
    public ResponseResult<GenerateModel> getGenerateModel(GenerateModel generateModel) {
        return ResponseResult.SUCCESS(generateService.getGenerateModel(generateModel));
    }

    @Override
    public ResponseResult generateModels(GenerateModel generateModel) {
        boolean success = generateService.generateModels(generateModel);
        if (success) {
            return ResponseResult.SUCCESS();
        }
        return ResponseResult.FAIL();
    }
}
