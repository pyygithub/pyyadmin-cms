package com.pyy.manage_cms.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.pyy.api.cms.CmsTemplateControllerApi;
import com.pyy.framework.domain.cms.CmsTemplate;
import com.pyy.framework.domain.cms.request.QueryTemplateRequest;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.exception.ExceptionCast;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.service.CmsTemplateService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ---------------------------
 * CMS页面模板管理Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-10 15:32:05
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class CmsTemplateController implements CmsTemplateControllerApi {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private CmsTemplateService cmsTemplateService;

    @Override
    public ResponseResult<List<CmsTemplate>> findList(QueryTemplateRequest queryTemplateRequest) {
        return ResponseResult.SUCCESS(cmsTemplateService.findList(queryTemplateRequest));
    }

    @Override
    public ResponseResult<QueryResult<CmsTemplate>> findList(int page, int size, QueryTemplateRequest queryTemplateRequest) {
        return ResponseResult.SUCCESS(cmsTemplateService.findList(page, size, queryTemplateRequest));
    }

    @Override
    public ResponseResult<CmsTemplate> add(CmsTemplate cmsTemplate) {
        return ResponseResult.SUCCESS(cmsTemplateService.add(cmsTemplate));
    }

    @Override
    public ResponseResult<CmsTemplate> findById(String id) {
        return ResponseResult.SUCCESS(cmsTemplateService.findById(id));
    }

    @Override
    public ResponseResult<CmsTemplate> edit(String id, CmsTemplate cmsTemplate) {
        return ResponseResult.SUCCESS(cmsTemplateService.update(id, cmsTemplate));
    }

    @Override
    public ResponseResult delete(String id) {
        cmsTemplateService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    @CrossOrigin
    public ResponseResult upload(MultipartFile file) {
        // 把时间戳作为文件名存入mongodb
        String fileName = String.valueOf(System.currentTimeMillis());
        try {
            // 定义输入流
            InputStream inputStream = file.getInputStream();
            //向GridFS存储文件
            ObjectId objectId = gridFsTemplate.store(inputStream, fileName);
            //得到文件ID
            String fileId = objectId.toString();
            return ResponseResult.SUCCESS(fileId);
        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.cast(CmsCode.CMS_TEMPLATEFILE_UPLOAD_ERROR);
        }
        return ResponseResult.FAIL();
    }
}
