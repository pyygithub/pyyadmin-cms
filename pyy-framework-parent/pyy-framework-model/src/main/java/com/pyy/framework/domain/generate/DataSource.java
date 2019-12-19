package com.pyy.framework.domain.generate;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@ToString
@Document(collection = "data_source")
public class DataSource {
    @Id
    private String id;
    private String name;
    private String dbType;
    private String host;
    private String port;
    private String username;
    private String password;
    private String dbName;
    private String createId;
    private String createName;
    private Date createTime;
    private String updateId;
    private String updateName;
    private Date updateTime;
}
