package com.example.datatype;

import com.example.demo.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class DataTypeMap {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        redisTemplate.opsForHash().put("key","user1",1);
        redisTemplate.opsForHash().increment("key","user1",1);
        redisTemplate.opsForHash().increment("key","user1",1);
        redisTemplate.opsForHash().increment("key","user1",1);
        redisTemplate.opsForHash().increment("key","user1",1);

    }
}
