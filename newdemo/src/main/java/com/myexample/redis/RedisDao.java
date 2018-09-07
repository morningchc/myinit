package com.myexample.redis;

/**
 * Created by Administrator on 2018/8/15.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisDao {

    @Autowired
    private StringRedisTemplate template;

    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, Object> vOps; //直接注入StringRedisTemplate

    @Autowired
    private RedisTemplate redisTemplate;

    //StringRedisTemplate template;
    public void newsetKey(String key,String value){
        vOps.set(key,value);//1分钟过期
    }
    public String newgetValue(String key){
        return (String) vOps.get(key);
    }

    public void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value,1, TimeUnit.MINUTES);//1分钟过期
    }
    public String getValue(String key){
        ValueOperations<String, String> ops = template.opsForValue();
        return ops.get(key);
    }
    public void setKey(String key,String value,Long expire){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
        template.expire(key, expire, TimeUnit.SECONDS);
    }
    public void expire(String key,Long expire){
        ValueOperations<String, String> ops = template.opsForValue();
        template.expire(key, expire, TimeUnit.SECONDS);
    }
    public boolean exists(final String key){
        boolean result = false;
        result = template.hasKey(key);
        return result;
    }
    public boolean delKey(String key){
        boolean result = false;
        if(exists(key)){
            template.delete(key);
        }
        return result;
    }
    //RedisTemplate redisTemplate
    public void setObjectTest(String key,Object object){
        ValueOperations<String,Object> rpos = redisTemplate.opsForValue();
        rpos.set(key,object);
    }

    public <T> T getObject(String key, Class<T> cls) {
        ValueOperations<String,Object> rpos = redisTemplate.opsForValue();
        return cls.cast(rpos.get(key));
    }



//    public Object getObjectTest(String key){
//        ValueOperations<String,Object> rpos = redisTemplate.opsForValue();
//        return rpos.get(key);
//    }
}
