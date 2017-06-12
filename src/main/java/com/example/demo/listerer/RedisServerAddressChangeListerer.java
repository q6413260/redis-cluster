package com.example.demo.listerer;

import com.example.demo.util.ZkPathConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.BinaryJedisCluster;

import javax.annotation.PostConstruct;

/**
 * Created by xiaoming on 12/06/2017.
 */
@Configuration
public class RedisServerAddressChangeListerer {
    @Autowired
    private CuratorFramework   curatorClient;

    @Autowired
    private BinaryJedisCluster redisClient;

    @PostConstruct
    public void init() {
        final NodeCache nodeCache = new NodeCache(curatorClient, ZkPathConstants.REDIS_TEST, false);
        nodeCache.getListenable()
                .addListener(() -> System.out.println(new String(nodeCache.getCurrentData().getData())));
    }
}
