package com.pyy.manage_cms.service;

import com.pyy.framework.domain.cms.CmsConfig;

public interface CmsConfigService {

    /**
     * 根据ID查询数据模型数据
     * @param id
     * @return
     */
    CmsConfig getConfigById(String id);
}
