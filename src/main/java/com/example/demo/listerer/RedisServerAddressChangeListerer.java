package com.example.demo.listerer;

import com.example.demo.redis.RedisClientManager;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.ZkPathConstants;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.BinaryJedisCluster;
import redis.clients.jedis.HostAndPort;

import javax.annotation.PostConstruct;
import java.util.Set;

/**
 * Created by xiaoming on 12/06/2017.
 */
@Configuration
public class RedisServerAddressChangeListerer {
    @Autowired
    private CuratorFramework curatorClient;

    @PostConstruct
    public void init() throws Exception {
        final NodeCache nodeCache = new NodeCache(curatorClient, ZkPathConstants.REDIS_SERVER_ADDRESS_PATH, false);
        nodeCache.getListenable().addListener(() -> {
            String redisServerAddress = new String(nodeCache.getCurrentData().getData());
            Set<HostAndPort> set = RedisUtils.parseAddress(redisServerAddress);
            BinaryJedisCluster redisClient = new BinaryJedisCluster(set);
            RedisClientManager.getInstance().setRedisClient(redisClient);
        });
        nodeCache.start();
    }
}
