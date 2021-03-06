package com.pyy.manage_cms.service.impl;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.CmsTemplate;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.exception.ExceptionCast;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.framework.model.response.ResponseResult;
import com.pyy.manage_cms.config.RabbitmqConfig;
import com.pyy.manage_cms.dao.CmsPageRepository;
import com.pyy.manage_cms.dao.CmsTemplateRepository;
import com.pyy.manage_cms.service.CmsPageService;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.RestTemplate;
import freemarker.template.Configuration;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CmsPageServiceImpl implements CmsPageService{

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public QueryResult<CmsPage> findList(int page, int size, QueryPageRequest queryPageRequest) {
        if(queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage = new CmsPage();
        //设置条件值（站点id）
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //设置模板id作为查询条件
        if(StringUtils.isNotEmpty(queryPageRequest.getTemplateId())){
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        //设置页面别名作为查询条件
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        //定义条件对象Example
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        //分页参数
        if(page <=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);//实现自定义条件查询并且分页查询
        QueryResult<CmsPage> queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        return queryResult;
    }

    @Override
    public CmsPage add(CmsPage cmsPage) {
        // 校验页面是否存在，根据页面名称、站点ID、页面webpath查询
        CmsPage oldCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        // 已存在则抛出异常
        if (oldCmsPage != null) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPage.setPageId(null);//添加页面主键由spring data 自动生成
        cmsPageRepository.save(cmsPage);
        // 返回结果
        return cmsPage;
    }

    @Override
    public CmsPage update(String id, CmsPage cmsPage) {
        // 根据ID查询页面信息
        CmsPage one = this.findById(id);
        if (one == null) {
            // 页面数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        //更新模板id
        one.setTemplateId(cmsPage.getTemplateId());
        //更新所属站点
        one.setSiteId(cmsPage.getSiteId());
        //更新页面别名
        one.setPageAliase(cmsPage.getPageAliase());
        //更新页面名称
        one.setPageName(cmsPage.getPageName());
        //更新访问路径
        one.setPageWebPath(cmsPage.getPageWebPath());
        //更新物理路径
        one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
        //更新dataUrl
        one.setDataUrl(cmsPage.getDataUrl());

        // 校验页面是否存在，根据页面名称、站点ID、页面webpath查询
        CmsPage oldCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (oldCmsPage != null && !oldCmsPage.getPageId().equals(id)) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        // 执行更新
        CmsPage save = cmsPageRepository.save(one);
        if (save == null) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 返回更新后对象
        return save;
    }

    @Override
    public CmsPage findById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        CmsPage cmsPage = this.findById(id);
        if (cmsPage == null) {
            // 页面数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        // 删除页面
        cmsPageRepository.deleteById(id);
    }

    @Override
    public String getPageHtml(String pageId) {
        // 1.获取页面模板数据
        Map model = this.getModelByPageId(pageId);
        if(model == null){
            //获取页面模型数据为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAISNULL);
        }

        // 2.获取页面模板
        String templateContent = getTemplateByPageId(pageId);
        if(StringUtils.isEmpty(templateContent)){
            //页面模板为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }

        // 3.执行静态化
        String html = generateHtml(templateContent, model);
        if(StringUtils.isEmpty(html)){
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        return html;
    }

    @Override
    public ResponseResult postPage(String pageId) {
        // 1.执行静态化
        String pageHtml = this.getPageHtml(pageId);

        // 2. 保存静态html文件到GridFS
        saveHtml(pageId, pageHtml);

        // 3. 发送mq消息
        sendPostPage(pageId);

        // 4.返回成功信息
        log.info("### 页面发布成功 ###");
        return ResponseResult.SUCCESS();
    }

    // 发送页面发布mq消息
    private void sendPostPage(String pageId) {
        // 校验页面数据是否存在
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        Map<String, String> msgMap = new HashMap<>(1);
        msgMap.put("pageId", pageId);
        // 消息内容
        String msg = JSON.toJSONString(msgMap);
        // 获取站点ID作为routingKey
        String siteId = cmsPage.getSiteId();
        // 发布消息
        this.rabbitTemplate.convertAndSend(RabbitmqConfig.EX_ROUTING_CMS_POSTPAGE, siteId, msg);
    }

    // 保存静态文件内容
    private CmsPage saveHtml(String pageId, String pageHtml) {
        // 查询页面
        CmsPage cmsPage = this.findById(pageId);
        if (cmsPage == null) {
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        // 存储之前先删除
        String htmlFileId = cmsPage.getHtmlFileId();
        if (StringUtils.isNotBlank(htmlFileId)) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(htmlFileId)));
        }
        ObjectId objectId = null;
        try {
            // 保存html文件到GridFS
            InputStream inputStream = IOUtils.toInputStream(pageHtml, "utf-8");
            // 将html文件到GridFS
            objectId = gridFsTemplate.store(inputStream, cmsPage.getPageName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 将文件ID存储到cmsPage中
        cmsPage.setHtmlFileId(objectId.toString());
        cmsPageRepository.save(cmsPage);
        return cmsPage;
    }

    // 页面静态化
    private String generateHtml(String templateContent, Map model) {
        try {
            // 生成配置类
            Configuration configuration = new Configuration(Configuration.getVersion());
            // 模板加载器
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate("template", templateContent);
            // 配置模板加载器
            configuration.setTemplateLoader(stringTemplateLoader);
            // 获取模板
            Template template = configuration.getTemplate("template");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取页面模板
    private String getTemplateByPageId(String pageId) {
        //查询页面信息
        CmsPage cmsPage = this.findById(pageId);
        if(cmsPage == null){
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        // 取出页面模板ID
        String templateId = cmsPage.getTemplateId();
        if(StringUtils.isEmpty(templateId)){
            //页面模板为空
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_TEMPLATEISNULL);
        }
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(templateId);
        if (optional.isPresent()) {
            CmsTemplate cmsTemplate = optional.get();
            // 取出模板文件ID
            String templateFileId = cmsTemplate.getTemplateFileId();
            // 取出gridFsFile对象
            GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(templateFileId)));
            // 打开下载流对象
            GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
            // 创建GridFsResource
            GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
            try {
                String content = IOUtils.toString(gridFsResource.getInputStream(), "utf-8");
                return content;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    // 获取页面模板数据
    private Map getModelByPageId(String pageId) {
        // 查询页面信息
        CmsPage cmsPage = this.findById(pageId);
        if(cmsPage == null) {
            //页面不存在
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        // 取出dataUrl
        String dataUrl = cmsPage.getDataUrl();
        if (StringUtils.isEmpty(dataUrl)) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_DATAURLISNULL);
        }
        ResponseEntity<Map> forEntity = restTemplate.getForEntity(dataUrl, Map.class);
        Map body = forEntity.getBody();

        return body;
    }
}
