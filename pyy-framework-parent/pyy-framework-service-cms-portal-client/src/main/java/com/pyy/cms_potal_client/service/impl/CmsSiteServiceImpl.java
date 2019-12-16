package com.pyy.cms_potal_client.service.impl;

import com.pyy.cms_potal_client.dao.CmsSiteRepository;
import com.pyy.cms_potal_client.service.CmsSiteService;
import com.pyy.framework.domain.cms.CmsSite;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CmsSiteServiceImpl implements CmsSiteService {

    @Autowired
    private CmsSiteRepository cmsSiteRepository;


    @Override
    public CmsSite findById(String id) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

}
