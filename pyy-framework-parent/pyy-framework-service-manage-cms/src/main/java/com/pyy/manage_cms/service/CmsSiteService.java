package com.pyy.manage_cms.service;

import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.QueryResult;

import java.util.List;

/**
 * ========================
 * 站点service接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 16:02
 * Version: v1.0
 * ========================
 */
public interface CmsSiteService {

    /**
     * 站点列表查询
     * @param querySiteRequest
     * @return
     */
    List<CmsSite> findList(QuerySiteRequest querySiteRequest) ;

    /**
     * 站点列表分页查询
     * @param page
     * @param size
     * @param querySiteRequest
     * @return
     */
    QueryResult<CmsSite> findList(int page, int size, QuerySiteRequest querySiteRequest) ;

    /**
     * 添加站点
     * @param cmsSite
     * @return
     */
    CmsSite add(CmsSite cmsSite);

    /**
     * 修改站点
     * @param id
     * @param cmsSite
     * @return
     */
    CmsSite update(String id, CmsSite cmsSite);

    /**
     * 根据ID查询站点
     * @param id
     * @return
     */
    CmsSite findById(String id);

    /**
     * 根据ID删除页眉
     * @param id
     * @return
     */
    void delete(String id);
}
