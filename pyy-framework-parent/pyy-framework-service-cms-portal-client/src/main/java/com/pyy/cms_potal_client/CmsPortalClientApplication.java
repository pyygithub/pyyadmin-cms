package com.pyy.cms_potal_client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * ---------------------------
 * CMS门户客户端启动类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 16:47
 * 版本：  v1.0
 * ---------------------------
 */
@EntityScan("com.pyy.framework.domain.cms") //扫描实体类
@ComponentScan(basePackages={"com.pyy.framework"})//扫描common的所有类
@ComponentScan(basePackages={"com.pyy.cms_potal_client"})//扫描本项目下的所有类
@SpringBootApplication
public class CmsPortalClientApplication {
}
