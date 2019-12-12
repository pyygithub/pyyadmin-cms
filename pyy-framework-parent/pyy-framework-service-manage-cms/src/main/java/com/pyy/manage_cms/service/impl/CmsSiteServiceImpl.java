package com.pyy.manage_cms.service.impl;

import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.domain.cms.response.CmsCode;
import com.pyy.framework.exception.ExceptionCast;
import com.pyy.framework.model.response.CommonCode;
import com.pyy.framework.model.response.QueryResult;
import com.pyy.manage_cms.dao.CmsSiteRepository;
import com.pyy.manage_cms.service.CmsSiteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ========================
 * 站点service实现类
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 16:02
 * Version: v1.0
 * ========================
 */
@Service
@Slf4j
public class CmsSiteServiceImpl implements CmsSiteService{

    @Autowired
    private CmsSiteRepository cmsSiteRepository;

    @Override
    public QueryResult findList(int page, int size, QuerySiteRequest querySiteRequest) {
        if(querySiteRequest == null){
            querySiteRequest = new QuerySiteRequest();
        }
        //自定义条件查询
        //定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("siteName", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsSite cmsSite = new CmsSite();
        //设置条件值（站点名称）
        if(StringUtils.isNotEmpty(querySiteRequest.getSiteName())){
            cmsSite.setSiteName(querySiteRequest.getSiteName());
        }
        //定义条件对象Example
        Example<CmsSite> example = Example.of(cmsSite,exampleMatcher);
        //分页参数
        if(page <=0){
            page = 1;
        }
        page = page -1;
        if(size<=0){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size);
        Page<CmsSite> all = cmsSiteRepository.findAll(example, pageable);//实现自定义条件查询并且分页查询
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        return queryResult;
    }

    @Override
    public CmsSite add(CmsSite cmsSite) {
        // 校验站点是否存在，根据站点名称、站点ID、站点webpath查询
        CmsSite oldCmsSite = cmsSiteRepository.findBySiteName(cmsSite.getSiteName());
        // 已存在则抛出异常
        if (oldCmsSite != null) {
            ExceptionCast.cast(CmsCode.CMS_ADDSITE_EXISTSNAME);
        }
        cmsSite.setSiteId(null);//添加站点主键由spring data 自动生成
        cmsSiteRepository.save(cmsSite);
        // 返回结果
        return cmsSite;
    }

    @Override
    public CmsSite update(String id, CmsSite cmsSite) {
        // 根据ID查询站点信息
        CmsSite one = this.findById(id);
        if (one == null) {
            // 站点数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }

        one.setSiteId();
        one.setSiteName();
        one.setSiteDomain();
        one.setSitePort();
        one.setSiteWebPath();
        one.setSiteCreateTime();
        // 校验站点是否存在，根据站点名称、站点ID、站点webpath查询
        CmsSite oldCmsSite = cmsSiteRepository.findBySiteName(cmsSite.getSiteName());
        if (oldCmsSite != null && !oldCmsSite.getSiteId().equals(id)) {
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        // 执行更新
        CmsSite save = cmsSiteRepository.save(one);
        if (save == null) {
            ExceptionCast.cast(CommonCode.FAIL);
        }
        // 返回更新后对象
        return save;
    }

    @Override
    public CmsSite findById(String id) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        CmsSite cmsSite = this.findById(id);
        if (cmsSite == null) {
            // 站点数据未找到，抛出异常
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOT_FOUND);
        }
        // 删除站点
        cmsSiteRepository.deleteById(id);
    }
}
