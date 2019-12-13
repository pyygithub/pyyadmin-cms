package com.pyy.manage_cms.service.impl;

import com.pyy.framework.domain.cms.CmsConfig;
import com.pyy.framework.domain.cms.CmsPage;
import com.pyy.manage_cms.dao.CmsConfigRepository;
import com.pyy.manage_cms.dao.CmsPageRepository;
import com.pyy.manage_cms.service.CmsConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CmsConfigServiceImpl implements CmsConfigService{

    @Autowired
    private CmsConfigRepository cmsConfigRepository;

    @Override
    public CmsConfig getConfigById(String id) {
        Optional<CmsConfig> optional = cmsConfigRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
