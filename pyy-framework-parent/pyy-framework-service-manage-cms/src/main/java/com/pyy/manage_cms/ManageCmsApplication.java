package com.pyy.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * ========================
 * ManageCms启动类
 * Created with IntelliJ IDEA.
 * User：pyy1
 * Date：2019/12/10 15:14
 * Version: v1.0
 * ========================
 */
@EntityScan("com.pyy.framework.domain.cms") //扫描实体类
@ComponentScan(basePackages={"com.pyy.api"})//扫描接口
@ComponentScan(basePackages={"com.pyy.framework"})//扫描common的所有类
@ComponentScan(basePackages={"com.pyy.manage_cms"})//扫描本项目下的所有类
@SpringBootApplication
public class ManageCmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class, args);
    }
}