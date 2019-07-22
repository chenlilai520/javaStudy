package com.spring.springstudy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;

/**
 * @author chenlilai
 * @title: RedisUtil
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/1714:08
 */
public class RedisUtil {


    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取锁
     * @param lockName
     * @param timeout
     * @return
     */
    public String lockWithTimeout(String lockName, long timeout) {
        String retIdentifier = null;

        //随机生成一个value
        String identifier =UUID.randomUUID().toString();

        //锁名 即key值
        String lockKey =  "lock :"+lockName;

        int lockExpire =(int) (timeout/1000);

        while (true){

        }

    }
}

