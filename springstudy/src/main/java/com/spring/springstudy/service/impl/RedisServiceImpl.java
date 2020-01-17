package com.spring.springstudy.service.impl;

import com.spring.springstudy.service.RedisService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public void addRedis() {
        RLock lock=null;
        try {
            lock=redissonClient.getLock("ss");
            boolean res=lock.tryLock(5,5,TimeUnit.SECONDS);  //最多等待5秒 加锁时间5秒
            if(res){
                String key = redisTemplate.opsForValue().get("key").toString();
                redisTemplate.opsForValue().set("key",Integer.valueOf(key)+1);
                System.out.println(redisTemplate.opsForValue().get("key").toString());
                //业务

               // redisTemplate.multi();// 开启一个事务
                //redisTemplate.exec();//提交事务
            }
        } catch (InterruptedException e) {

        }finally {
            if (lock != null) {
                lock.unlock();
            }
        }
    }

    @Override
    public void threadRun() {
        redisTemplate.opsForValue().set("key",0);
        redisTemplate.watch("ss"); // 监听
       for(int i=0;i<5;i++){
           new Thread(() -> {
               addRedis();

           }).start();
       }
    }
}
