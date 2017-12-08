package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * Created by alcava00 on 2017. 7. 14..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTest {
    //    @Autowired
//    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    HashOperations<String, byte[], byte[]> hashOperations;

    @Resource(name="redisTemplate")
    private ListOperations<String, Object> listOps;

    @Resource(name="redisTemplate")
    private SetOperations<String, String> setOperations;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Resource(name="redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

//    @Test
    public void testRedis() {
        Map<byte[], byte[]> hash = mapper.toHash(new Person("Lee","alcava00"));
        hashOperations.putAll("aaaa",hash);
        listOps.leftPush("bbbb",new Person("Lee2","alcava00"));
        System.out.println(" listOps.leftPop(\"AAAA\") " +  mapper.fromHash(  hashOperations.entries("aaaa")));
        System.out.println("String object" +  listOps.leftPop("bbbb"));
    }


    @Test
    public void testSetOperation() {
        long time=System.currentTimeMillis();
        String orginkey="orgin2";
        for(int i=0;i<100;i++){
            setOperations.add("orgin",""+ Math.round(  Math.random()*100000000));
        }
        for(int i=0;i<100;i++){
            setOperations.add("orgin2",""+ Math.round(  Math.random()*100000000));
        }

        Set<String> inersectvalues= setOperations.intersect("orgin2","orgin");

        for(String value:inersectvalues){
            System.out.println("Values : " + value);
        }



//        System.out.println("inersectvalues Size : " + inersectvalues.size());
        System.out.println("orgin1 Size : " + setOperations.size("orgin"));
        System.out.println("orgin2 Size : " + setOperations.size("orgin2"));
        System.out.println(" setOperations.pop(\"orgin2\"): " +  setOperations.pop("orgin2"));
        System.out.println(">>>>>>>>>" + (System.currentTimeMillis()-time));

    }

    @Test
    public void testZSetOperations() {
        long time=System.currentTimeMillis();
        String orginkey="orgin2";


        for(int i=0;i<100;i++){
            setOperations.add("orgin",""+ Math.round(  Math.random()*100000000));
        }
        for(int i=0;i<100;i++){
            setOperations.add("orgin2",""+ Math.round(  Math.random()*100000000));
        }

        Set<String> inersectvalues= setOperations.intersect("orgin2","orgin");

        for(String value:inersectvalues){
            System.out.println("Values : " + value);
        }



//        System.out.println("inersectvalues Size : " + inersectvalues.size());
        System.out.println("orgin1 Size : " + setOperations.size("orgin"));
        System.out.println("orgin2 Size : " + setOperations.size("orgin2"));
        System.out.println(" setOperations.pop(\"orgin2\"): " +  setOperations.pop("orgin2"));
        System.out.println(">>>>>>>>>" + (System.currentTimeMillis()-time));

    }


}
