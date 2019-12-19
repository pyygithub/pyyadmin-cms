package com.pyy.manage_generate.dao;

import com.pyy.framework.domain.generate.DataSource;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataSourceRepository extends MongoRepository<DataSource, String> {

    DataSource findByDbTypeAndUsernameAndDbName(String dbType, String username, String dbName);
}
