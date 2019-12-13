package com.pyy.manage_cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * ---------------------------
 * ManageCms启动类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-13 14:30:05
 * 版本：  v1.0
 * ---------------------------
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }
}