package com.spring.springstudy.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenlilai
 * @title: LRUCache
 * @projectName javaStudy1
 * @description:
 * @date 2019/9/213:55
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {

    private final  int CACHE_SIZE;

    public LRUCache(int cacheSize) {
        super((int) (Math.ceil(cacheSize/0.75)+1),0.75f,true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {

        return size()>CACHE_SIZE;
    }
}
