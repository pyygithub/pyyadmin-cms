package com.pyy.cms_potal_client.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pyy.cms_potal_client.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ---------------------------
 * 发布页面的消费客户端
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 17:47
 * 版本：  v1.0
 * ---------------------------
 */
@Component
@Slf4j
public class ConsumerPostPage {

    @Autowired
    private CmsPageService cmsPageService;

    @RabbitListener(queues={"${cms.mq.queue}"})
    public void postPage(String msg){
        // 解析消息
        JSONObject jsonObject = JSON.parseObject(msg);
        log.info("receive cms post page:{}", jsonObject.toString());
        // 取出页面ID
        String pageId = jsonObject.getString("pageId");

        // 将页面保存到服务器物理路径
        cmsPageService.savePageToServerPath(pageId);
    }
}
