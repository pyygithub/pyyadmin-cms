package com.pyy.manage_cms.service.impl;

import com.pyy.framework.domain.cms.CmsTemplate;
import com.pyy.framework.domain.cms.request.QueryTemplateRequest;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.exception.ExceptionCast;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.manage_cms.dao.CmsTemplateRepository;
import com.pyy.manage_cms.service.CmsTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CmsTemplateServiceImpl implements CmsTemplateService{

    @Autowired
    private CmsTemplateRepository cmsTemplateRepository;

    @Override
    public List<CmsTemplate> findList(QueryTemplateRequest queryTemplateRequest) {
        if(queryTemplateRequest == null){
            queryTemplateRequest = new QueryTemplateRequest();
        }
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsTemplate cmsTemplate = new CmsTemplate();
        //设置条件值（站点名称）
        if(StringUtils.isNotEmpty(queryTemplateRequest.getTemplateName())){
            cmsTemplate.setTemplateName(queryTemplateRequest.getTemplateName());
        }
        //定义条件对象Example
        Example<CmsTemplate> example = Example.of(cmsTemplate,exampleMatcher);
        List<CmsTemplate> all = cmsTemplateRepository.findAll(example);
        return all;
    }

    @Override
    public QueryResult<CmsTemplate>                                                                                                                                                                                           findList(int page, int size, QueryTemplateRequest queryTemplateRequest) {
        if(queryTemplateRequest == null){
            queryTemplateRequest = new QueryTemplateRequest();
        }
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("templateName", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsTemplate cmsTemplate = new CmsTemplate();
        //设置条件值（站点名称）
        if(StringUtils.isNotEmpty(queryTemplateRequest.getTemplateName())){
            cmsTemplate.setTemplateName(queryTemplateRequest.getTemplateName());
        }
        //定义条件对象Example
        Example<CmsTemplate> example = Example.of(cmsTemplate,exampleMatcher);
        //分页参数
        if(page <=0){
            page = 1;
        }
        page = page - 1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsTemplate> all = cmsTemplateRepository.findAll(example, pageable);//实现自定义条件查询并且分页查询
        QueryResult<CmsTemplate> queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        return queryResult;
    }

    @Override
    public CmsTemplate add(CmsTemplate cmsTemplate) {
        // 校验站点是否存在，根据站点名称、站点ID、站点webpath查询
        CmsTemplate oldCmsTemplate = cmsTemplateRepository.findByTemplateName(cmsTemplate.getTemplateName());
        // 已存在则抛出异常
        if (oldCmsTemplate != null) {
            ExceptionCast.cast(CmsCode.CMS_ADDSITE_EXISTSNAME);
        }
        cmsTemplate.setTemplateId(null);//添加站点主键由spring data 自动生成
        cmsTemplateRepository.save(cmsTemplate);
        // 返回结果
        return cmsTemplate;
    }

    @Override
    public CmsTemplate update(String id, CmsTemplate cmsTemplate) {
        // 根据ID查询站点信息
        CmsTemplate one = this.findById(id);
        if (one == null) {
            // 站点数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_SITE_NOT_FOUND);
        }
        one.setTemplateName(cmsTemplate.getTemplateName());
        one.setTemplateParameter(cmsTemplate.getTemplateParameter());
        one.setTemplateFileId(cmsTemplate.getTemplateFileId());
        // 校验站点是否存在，根据站点名称查询
        CmsTemplate oldCmsTemplate = cmsTemplateRepository.findByTemplateName(cmsTemplate.getTemplateName());
        if (oldCmsTemplate != null && !oldCmsTemplate.getTemplateId().equals(id)) {
            ExceptionCast.cast(CmsCode.CMS_ADDSITE_EXISTSNAME);
        }
        // 执行更新
        CmsTemplate save = cmsTemplateRepository.save(one);
        if (save == null) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 返回更新后对象
        return save;
    }

    @Override
    public CmsTemplate findById(String id) {
        Optional<CmsTemplate> optional = cmsTemplateRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        CmsTemplate cmsTemplate = this.findById(id);
        if (cmsTemplate == null) {
            // 站点数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDSITE_EXISTSNAME);
        }
        // 删除站点
        cmsTemplateRepository.deleteById(id);
    }
}
