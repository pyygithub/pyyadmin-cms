package com.pyy.manage_cms.service;

import com.pyy.framework.domain.cms.CmsTemplate;
import com.pyy.framework.domain.cms.request.QueryTemplateRequest;
import com.pyy.framework.model.response.QueryResult;

import java.util.List;

public interface CmsTemplateService {

    /**
     * 模板列表查询
     * @param queryTemplateRequest
     * @return
     */
    List<CmsTemplate> findList(QueryTemplateRequest queryTemplateRequest) ;

    /**
     * 模板列表分页查询
     * @param page
     * @param size
     * @param queryTemplateRequest
     * @return
     */
    QueryResult<CmsTemplate> findList(int page, int size, QueryTemplateRequest queryTemplateRequest) ;

    /**
     * 添加模板
     * @param cmsTemplate
     * @return
     */
    CmsTemplate add(CmsTemplate cmsTemplate);

    /**
     * 修改模板
     * @param id
     * @param cmsTemplate
     * @return
     */
    CmsTemplate update(String id, CmsTemplate cmsTemplate);

    /**
     * 根据ID查询模板
     * @param id
     * @return
     */
    CmsTemplate findById(String id);

    /**
     * 根据ID删除页眉
     * @param id
     * @return
     */
    void delete(String id);
}
