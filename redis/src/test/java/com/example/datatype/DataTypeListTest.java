package com.example.datatype;

import com.example.demo.config.RedisConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class DataTypeListTest extends Assert {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testInsertList() {
        redisTemplate.opsForList().rightPush("user","lee");
        redisTemplate.opsForList().rightPush("user","lee1");
        redisTemplate.opsForList().rightPush("user","lee3");
        System.out.println( redisTemplate.opsForList().leftPop("user"));
        System.out.println( redisTemplate.opsForList().leftPop("user"));
        System.out.println( redisTemplate.opsForList().leftPop("user"));
        assertNull(redisTemplate.opsForList().leftPop("user"));
    }

}
