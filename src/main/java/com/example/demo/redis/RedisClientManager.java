package com.example.demo.redis;

import redis.clients.jedis.BinaryJedisCluster;

/**
 * Created by xiaoming on 15/06/2017.
 */
public class RedisClientManager {

    private static RedisClientManager redisClientManager = new RedisClientManager();
    private BinaryJedisCluster redisClient;

    private RedisClientManager() {
    }

    public static synchronized RedisClientManager getInstance() {
        return redisClientManager;
    }

    public void setRedisClient(BinaryJedisCluster redisClient){
        this.redisClient = redisClient;
    }

    public BinaryJedisCluster getRedisClient(){
        return redisClient;
    }
}
