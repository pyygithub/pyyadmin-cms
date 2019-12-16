package com.pyy.manage_cms.service;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.model.response.QueryResult;

public interface CmsPageService {
    /**
     * 页面列表分页查询
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    QueryResult findList(int page, int size, QueryPageRequest queryPageRequest) ;

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    CmsPage add(CmsPage cmsPage);

    /**
     * 修改页面
     * @param id
     * @param cmsPage
     * @return
     */
    CmsPage update(String id, CmsPage cmsPage);

    /**
     * 根据ID查询页面
     * @param id
     * @return
     */
    CmsPage findById(String id);

    /**
     * 根据ID删除页眉
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 获取静态化页面
     * @param pageId
     * @return
     */
    String getPageHtml(String pageId);
}
