package com.ccm.dubboconsumer.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by chencm on 2018/12/5
 */
@Component
public class RedisComponent {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public <T> void setForValue(String key,final T obj){
        redisTemplate.opsForValue().set(key,obj);
    }

    public <T> T getForValue(String key){
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj==null)
            return null;
        return (T)redisTemplate.opsForValue().get(key);
    }

    public void setForList(String key,final List<?> obj){
        redisTemplate.opsForList().rightPushAll(key,obj);
    }

    public <T> T getForList(String key){
        return (T)redisTemplate.opsForList().range(key,0,-1);
    }
}
