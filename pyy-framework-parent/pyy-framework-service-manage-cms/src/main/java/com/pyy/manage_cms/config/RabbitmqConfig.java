package com.pyy.manage_cms.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ---------------------------
 * Rabbitmq配置类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/16 16:24
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
public class RabbitmqConfig {

    /** 交换机的名称 */
    public static final String EX_ROUTING_CMS_POSTPAGE = "ex_routing_cms_postpage";

    /**
     * 交换机配置使用direct类型
     * @return
     */
    @Bean(EX_ROUTING_CMS_POSTPAGE)
    public Exchange EXCHANGE_TOPICS_INFORM() {
        return ExchangeBuilder.directExchange(EX_ROUTING_CMS_POSTPAGE).durable(true).build();
    }
}
