package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    private BinaryJedisCluster redisClient;

    @RequestMapping(value = "/set")
    public String set(@RequestParam("key")String key, @RequestParam("value")String value){
        redisClient.set(key.getBytes(), value.getBytes());
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
