package com.pyy.manage_cms.dao;

import com.pyy.framework.domain.cms.CmsSite;
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
public interface CmsSiteRepository extends MongoRepository<CmsSite, String>{


    /**
     * 根据名称查询
     * @param siteName
     * @return
     */
    CmsSite findBySiteName(String siteName);

    /**
     * 根据站点和页面类型分页查询
     * @param siteName
     * @param pageable
     * @return
     */
    Page<CmsSite> findBySiteName(String siteName, Pageable pageable);
}
