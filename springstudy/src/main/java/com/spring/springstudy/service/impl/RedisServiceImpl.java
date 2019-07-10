package com.spring.springstudy.service.impl;

import com.spring.springstudy.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chenlilai
 * @title: RedisServiceImpl
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/1016:06
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void addRedis() {

        redisTemplate.opsForValue().set("1","2");
        System.out.println(redisTemplate.opsForValue().get(1));
    }
}
