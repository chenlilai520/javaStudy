package com.spring.springstudy.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author chenlilai
 * @title: RedisConfigBean
 * @projectName javaStudy1
 * @description:
 * @date 2019/7/1111:44
 */
@Configuration
public class RedisConfigBean {

   /**
    * redis 防止key value 前缀乱码.
    *
    * @param factory redis连接 factory
    * @return redisTemplate
    */
   @Bean(name = "redisTemplate")
   public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
     RedisTemplate<String, Object> template = new RedisTemplate<>();
     template.setConnectionFactory(factory);
     template.setKeySerializer(new StringRedisSerializer());
     template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
     template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
     template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
     template.afterPropertiesSet();
     return template;
   }
 }
