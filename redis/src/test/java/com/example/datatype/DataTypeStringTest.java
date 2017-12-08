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
public class DataTypeStringTest extends Assert {
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "template")
    private ValueOperations<String, String> valueOperations;

    @Test
    public void testIncr() {
        valueOperations.set("count", "1234");
        valueOperations.increment("count", 1L);
        assertEquals("1235", valueOperations.get("count"));
    }

    @Test
    public void testExpire() throws InterruptedException {
        valueOperations.set("ex", "gogogo", 1, TimeUnit.SECONDS);
        assertEquals("gogogo", valueOperations.get("ex"));
        Thread.sleep(1000);
        assertNull(valueOperations.get("ex"));
    }

    @Test
    public void testBit() {
        valueOperations.setBit("bit", Integer.MAX_VALUE, true);
        valueOperations.setBit("bit", 102, true);
        valueOperations.setBit("bit", 103, true);
        valueOperations.setBit("bit", 104, true);
        valueOperations.setBit("bit", 107, false);

        System.out.println(valueOperations.getBit("bit", Integer.MAX_VALUE));
        System.out.println(valueOperations.getBit("bit", 101));
        System.out.println(valueOperations.getBit("bit", 110));
        System.out.println(valueOperations.get("bit").getBytes().length);
//        for (int i = 0; i < 3; i++) {
//            byte b = by[i];
//            for (int k = 0; k < 8; k++) {
//                if ((b & (1 << k)) != 0) {
//                    System.out.println("user: " + (i * 8 + k));
//                }
//            }
//        }
    }
}
