package com.zhh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 单元测试类
 * @Author: zhouhui
 * @Version: V1.0
 * @Date: 2019/4/5 15:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootRedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testStringRedis(){
        // Redis存储String字符串
        String name = "zhangsan";
        // key:name,存储时间为5，时间单位为秒
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("nameString",name,5, TimeUnit.SECONDS);
        System.out.println(valueOperations.get("nameString"));
    }

    @Test
    public void testListRedis(){
        // Redis存储List集合
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("nameList","zhangsan");
        listOperations.leftPush("nameList","lisi");
        List<String> list = listOperations.range("nameList",0,-1);
        if(!list.isEmpty()){
            for(String name : list){
                System.out.println(name);
            }
        }
    }

    @Test
    public void testHashRedis(){
        // Redis存储Hash
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.put("myHash","name","zhangsan");
        hashOperations.put("myHash","age","22");
        Map<String,String> map = hashOperations.entries("myHash");
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    @Test
    public void testSetRedis(){
        // Redis存储set
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("mySet","zhangsan");
        setOperations.add("mySet","lisi");
        Set<String> set = setOperations.members("mySet");
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testZSetRedis(){
        // Redis存储ZSet
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.add("zset","zhangsan",1.0);
        zSetOperations.add("zset","lisi",2.0);
        Set<ZSetOperations.TypedTuple<Object>> tuples = zSetOperations.rangeWithScores("zset",0,-1);
        Iterator<ZSetOperations.TypedTuple<Object>> iterator = tuples.iterator();
        while (iterator.hasNext())
        {
            ZSetOperations.TypedTuple<Object> typedTuple = iterator.next();
            System.out.println("value: " + typedTuple.getValue() + " score:" + typedTuple.getScore());
        }
    }


}
