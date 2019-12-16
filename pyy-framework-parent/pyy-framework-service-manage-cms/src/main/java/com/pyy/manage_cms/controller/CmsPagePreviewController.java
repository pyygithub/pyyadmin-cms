package com.pyy.manage_cms.controller;

import com.pyy.framework.web.BaseController;
import com.pyy.manage_cms.service.CmsPageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;

/**
 * ---------------------------
 * 页面预览Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 14:23
 * 版本：  v1.0
 * ---------------------------
 */
@Controller
@RequestMapping("/cms")
public class CmsPagePreviewController extends BaseController{

    @Autowired
    private CmsPageService cmsPageService;

    @GetMapping("/preview/{pageId}")
    public void preview(@PathVariable("pageId") String pageId) {
        String pageHtml = cmsPageService.getPageHtml(pageId);
        if (StringUtils.isNotBlank(pageHtml)) {
            try {
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(pageHtml.getBytes("utf-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
