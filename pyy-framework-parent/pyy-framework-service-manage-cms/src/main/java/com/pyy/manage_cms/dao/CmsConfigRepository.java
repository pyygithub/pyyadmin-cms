package com.pyy.manage_cms.dao;

import com.pyy.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsConfigRepository extends MongoRepository<CmsConfig, String>{
}
