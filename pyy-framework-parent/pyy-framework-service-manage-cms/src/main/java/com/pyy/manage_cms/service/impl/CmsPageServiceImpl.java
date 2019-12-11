package com.pyy.manage_cms.service.impl;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.domain.cms.response.CmsPageResult;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResponseResult;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.manage_cms.dao.CmsPageRepository;
import com.pyy.manage_cms.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * ========================
 * 页面service实现类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 16:02
 * Version: v1.0
 * ========================
 */
@Service
@Slf4j
public class CmsPageServiceImpl implements CmsPageService{

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Override
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
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
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    @Override
    public CmsPageResult add(CmsPage cmsPage) {
        // 校验页面是否存在，根据页面名称、站点ID、页面webpath查询
        CmsPage oldCmsPage = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (oldCmsPage == null) {
            cmsPage.setPageId(null);//添加页面主键由spring data 自动生成
            cmsPageRepository.save(cmsPage);
            // 返回结果
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,cmsPage);
            return cmsPageResult;
        }
        return  new CmsPageResult(CmsCode.CMS_ADDPAGE_EXISTSNAME,null);
    }
}
