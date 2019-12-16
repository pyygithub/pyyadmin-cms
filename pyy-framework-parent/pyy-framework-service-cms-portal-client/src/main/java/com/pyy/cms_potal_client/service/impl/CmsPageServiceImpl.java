package com.pyy.cms_potal_client.service.impl;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.pyy.cms_potal_client.dao.CmsPageRepository;
import com.pyy.cms_potal_client.dao.CmsSiteRepository;
import com.pyy.cms_potal_client.service.CmsPageService;
import com.pyy.cms_potal_client.service.CmsSiteService;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.exception.ExceptionCast;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

/**
 * ---------------------------
 * 页面Service实现类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 17:03
 * 版本：  v1.0
 * ---------------------------
 */
@Service
@Slf4j
public class CmsPageServiceImpl implements CmsPageService {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsSiteService cmsSiteService;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;


    @Override
    public void savePageToServerPath(String pageId) {
        // 根据页面ID查询页面信息
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if(!optional.isPresent()){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        CmsPage cmsPage = optional.get();
        // 取出站点ID
        String siteId = cmsPage.getSiteId();
        CmsSite cmsSite = cmsSiteService.findById(siteId);
        if (cmsSite == null) {
            ExceptionCast.cast(CmsCode.CMS_SITE_NOT_FOUND);
        }
        // 取出页面文件ID
        String htmlFileId = cmsPage.getHtmlFileId();
        // 查询页面静态文件
        InputStream inputStream = this.getFileById(htmlFileId);
        if (inputStream == null) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }

        // 拼接页面物理路径
        String pagePath = cmsSite.getSiteP

        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream(new File())

    }

    @Override
    public InputStream getFileById(String fileId) {
        try {
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
