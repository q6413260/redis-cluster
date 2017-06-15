package com.example.demo.controller;

import com.example.demo.redis.RedisClientManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaoming on 12/06/2017.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/")
    public String set(){
        new Thread(() -> {
            BinaryJedisCluster redisClient = RedisClientManager.getInstance().getRedisClient();
            for(int i=0; ; i++){
                String key = "foo" + i;
                redisClient.set(key.getBytes(), String.valueOf(i).getBytes());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "success";
    }

    public static void main(String[] args) {
        Set<HostAndPort> set = new HashSet<>();
        HostAndPort hostAndPort = new HostAndPort("127.0.0.1", 7000);
        set.add(hostAndPort);
        BinaryJedisCluster client = new BinaryJedisCluster(set);
        client.get("name".getBytes());
    }
}
