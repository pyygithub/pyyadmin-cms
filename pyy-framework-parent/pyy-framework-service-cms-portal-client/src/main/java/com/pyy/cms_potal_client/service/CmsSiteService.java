package com.pyy.cms_potal_client.service;

import com.pyy.framework.domain.cms.CmsSite;
import com.pyy.framework.domain.cms.request.QuerySiteRequest;
import com.pyy.framework.model.response.QueryResult;

import java.util.List;

public interface CmsSiteService {
    /**
     * 根据ID查询站点
     * @param id
     * @return
     */
    CmsSite findById(String id);

}
