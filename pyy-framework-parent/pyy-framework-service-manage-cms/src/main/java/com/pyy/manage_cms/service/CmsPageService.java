package com.pyy.manage_cms.service;

import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.framework.domain.cms.request.QueryPageRequest;
import com.pyy.framework.domain.cms.response.CmsPageResult;
import com.pyy.framework.model.response.QueryResponseResult;

/**
 * ========================
 * 页面service接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 16:02
 * Version: v1.0
 * ========================
 */
public interface CmsPageService {
    /**
     * 页面列表分页查询
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) ;

    /**
     * 添加页面
     * @param cmsPage
     * @return
     */
    CmsPageResult add(CmsPage cmsPage);
}
