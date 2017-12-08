package com.example.datatype;

import com.example.demo.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class DataTypeSetTest {

    @Autowired
    private RedisTemplate redisTemplate;

    public static int ramdom() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    @Test
    public void testSetDifference() {
        SetOperations<String, String> op = redisTemplate.opsForSet();
        SetOperations<String, Integer> op2 = redisTemplate.opsForSet();
        op.add("blackmember", "aaa", "bbb", "ccc");
        op.add("whitemember", "aaa", "xxx", "kkk");

        for (int i = 0; i < 10000; i++) {
            op2.add("black", ramdom());
            op2.add("white", ramdom());
        }

        long time = System.currentTimeMillis();
        Set<Integer> dif = op2.difference("black", "white");
        for (int member : dif) {
            System.out.println("dif member:" + member);
        }
        System.out.println("time:" + (System.currentTimeMillis() - time));

        op2.unionAndStore("black", "white", "bw");

    }

    @Test
    public void testSendMail() {
        markDealAsSent("deal:1", "user:1");
        markDealAsSent("deal:1", "user:2");
        markDealAsSent("deal:2", "user:1");
        markDealAsSent("deal:2", "user:3");
        sendDealIfNotSent("deal:1", "user:1");
        sendDealIfNotSent("deal:1", "user:2");
        sendDealIfNotSent("deal:1", "user:3");

        Set<String> s1 = showUsersThatReceivedAllDeals("deal:1", "deal:2");
        System.out.println("all received ********");
        for (Object user : s1.toArray()) {
            System.out.println("User:" + user);
        }
        showUsersThatReceivedAtLeastOneOfTheDeals("deal:1", "deal:2");
    }

    public void markDealAsSent(String dealId, String userId) {
        redisTemplate.opsForSet().add(dealId, userId);
    }

    public void sendDealIfNotSent(String dealId, String... userId) {
        for (String id : userId) {
            if (redisTemplate.opsForSet().isMember(dealId, id)) {
                System.out.println("send : " + id);
            } else {
                markDealAsSent(dealId, id);
            }
        }
    }

    public Set showUsersThatReceivedAllDeals(String... dealIds) {
        return redisTemplate.opsForSet().intersect(dealIds[0], Arrays.asList(dealIds));
    }

    public Set showUsersThatReceivedAtLeastOneOfTheDeals(String... dealIds) {
        return redisTemplate.opsForSet().union(dealIds[0], Arrays.asList(dealIds));
    }
}
