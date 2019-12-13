package com.pyy.manage_cms.dao;

import com.pyy.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsTemplateRepository extends MongoRepository<CmsTemplate, String>{

    CmsTemplate findByTemplateName(String templateName);
}
