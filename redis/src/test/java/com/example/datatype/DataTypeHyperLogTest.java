package com.example.datatype;

import com.example.demo.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class DataTypeHyperLogTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSimple(){
        String key="2017120717";
        redisTemplate.opsForHyperLogLog().delete("2017120717*");
        redisTemplate.opsForHyperLogLog().add(key,"aaa","bbbb","ccc");


        for (int j=0;j<10;j++){
            for(int i=0;i<10000;i++){
                redisTemplate.opsForHyperLogLog().add(key+j,i+"uu"+j);
            }
        }
        for (int j=0;j<10;j++){
            System.out.println(redisTemplate.opsForHyperLogLog().size(key+j));
        }

        System.out.println(redisTemplate.opsForHyperLogLog().union(key+0, Arrays.asList(key+1,key+2)));
    }

}
