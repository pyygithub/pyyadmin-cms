package com.pyy.cms_potal_client.service;

import java.io.InputStream;

/**
 * ---------------------------
 * 页面Service接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 16:59
 * 版本：  v1.0
 * ---------------------------
 */
public interface CmsPageService {
    /**
     * 将页面HTML保存到页面物理路径
     * @param pageId
     */
    void savePageToServerPath(String pageId);

    /**
     * 根据文件ID查询文件
     * @param fileId
     * @return
     */
    InputStream getFileById(String fileId);
}
