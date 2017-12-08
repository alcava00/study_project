package com.example.demo;

import com.example.demo.config.RedisConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= RedisConfig.class)
//@SpringBootTest
public class RedisTest extends Assert {
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "template")
    private ValueOperations<String, String> valueOperations;

    @Resource(name = "template")
    private ValueOperations<String, Boolean> valueOperations2;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOperations;

    @Test
    public void testValueOperations() {
        valueOperations.set("AA", "BB");
        assertEquals("BB", valueOperations.get("AA"));
    }

    @Test
    public void testListOperations() {
        listOps.getOperations().delete("AAA");
        listOps.leftPush("AAA", "BBBB1");
        listOps.leftPush("AAA", "BBBB2");
        listOps.leftPush("AAA", "BBBB3");

        assertEquals("BBBB3", listOps.leftPop("AAA"));
        listOps.leftPush("AAA", "BBBB4");
        assertEquals("BBBB4", listOps.leftPop("AAA"));

        System.out.println("  listOps.size(\"AAA\")>>>" + listOps.size("AAA"));
        System.out.println("  listOps.size(\"AAA\")>>>" + listOps.size("AAA"));


        System.out.println("listOps.index(\"AAA\",0);" + listOps.index("AAA",0));
        System.out.println("  listOps.size(\"AAA\")>>>" + listOps.size("AAA"));
    }

    @Test
    public void testSetOperation() {
        System.out.println(setOperations.members("K1").size());
        setOperations.add("K1", "AAA", "BBB", "CCC");
        System.out.println(" setOperations.pop(\"K1\") : " + setOperations.pop("K1"));
        System.out.println(setOperations.size("K1") );
        System.out.println(setOperations.pop("K1") );
        System.out.println(setOperations.pop("K1") );
        System.out.println(setOperations.size("K1") );
    }

    @Test
    public void testCounterVisitor() {
        valueOperations.setBit("counter:1001",8,true);
        valueOperations.setBit("counter:1001",9,true);
        valueOperations.setBit("counter:1001",10,true);

        String msg=valueOperations.get("counter:1001");

        byte[] bytes = msg.getBytes();
        StringBuilder binary = new StringBuilder();
        int count=0;
        for (byte b : bytes)
        {
            count+=Integer.bitCount(b);
        }

        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }

        System.out.println( "' to binary: " + binary);

        System.out.println(valueOperations.getBit("counter:1001",1007));
        System.out.println(  valueOperations.get("counter:1001"));
        System.out.println("count : " +count);
    }

}
