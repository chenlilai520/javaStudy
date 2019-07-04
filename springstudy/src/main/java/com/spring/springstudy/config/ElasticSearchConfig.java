package com.spring.springstudy.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author : zhongxianyin
 * @ClassName : com.cifmedia.config.es
 * @E-mail : music120326@hotmail.com
 * @date : 2019/4/10 15:49
 */
@Configuration
public class ElasticSearchConfig {

    /**
     * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
     * 解决netty冲突后初始化client时还会抛出异常
     * java.lang.IllegalStateException: availableProcessors is already set to [6], rejecting [6]
     */
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}