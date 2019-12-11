package com.pyy.manage_cms.dao;

import com.pyy.framework.domain.cms.CmsPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ========================
 * 页面DAO接口
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/12/10 15:43
 * Version: v1.0
 * ========================
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String>{

    /**
     * 根据页面名称查询
     * @param pageName
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据页面名称和类型查询
     * @param pageName
     * @param pageType
     * @return
     */
    CmsPage findByPageNameAndPageType(String pageName,String pageType);

    /**
     * 根据站点和页面类型查询记录数
     * @param siteId
     * @param pageType
     * @return
     */
    int countBySiteIdAndPageType(String siteId,String pageType);

    /**
     * 根据站点和页面类型分页查询
     * @param siteId
     * @param pageType
     * @param pageable
     * @return
     */
    Page<CmsPage> findBySiteIdAndPageType(String siteId, String pageType, Pageable pageable);

    /**
     * 根据页面名称和站点、页面类型查询
     * @param pageName
     * @param siteId
     * @param pageWebPath
     * @return
     */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);
}
