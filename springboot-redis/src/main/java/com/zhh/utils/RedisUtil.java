package com.zhh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description: Redis工具
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/3/31 12:13
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    // 过期时间：秒
    public static final long DEFAULT_EXPIRE_TIME = 24 * 60 * 60;

    // 设置永不过期：-1
    public static final long NONE_EXPIRE_TIME = -1;

    /**
     * 判断redis中是否存在指定key
     * @param key redis存储的key值
     * @return boolean true代表存在
     */
    public boolean existKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 重命名redis中指定的key
     * @param oldKey 旧的key值
     * @param newKey 新的key值
     */
    public void renameKey(String oldKey, String newKey){
        redisTemplate.rename(oldKey,newKey);
    }

    /**
     * 重命名redis中指定的key(如果newKey不存在)
     * @param oldKey 旧的key值
     * @param newKey 新的key值
     * @return boolean true代表修改成功
     */
    public boolean renameKeyIfAbsent(String oldKey, String newKey){
        return redisTemplate.renameIfAbsent(oldKey,newKey);
    }

    /**
     * 删除redis中指定的单个key(String参数重载)
     * @param key
     */
    public void deleteKey(String key){
        redisTemplate.delete(key);
    }

    /**
     * 删除redis中指定的多个key(可变参数重载)
     * @param keys
     */
    public void deleteKey(String... keys){
        Set<String> kSet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }

    /**
     * 删除redis中指定的多个key(集合参数重载)
     * @param keys
     */
    public void deleteKey(Collection<String> keys){
        Set<String> kSet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }


}
