package com.example.demo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisClusterApplication.class, args);
    }

    @Bean
    public CuratorFramework curatorClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorClient = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        curatorClient.start();
        try {
            if (!curatorClient.getZookeeperClient().blockUntilConnectedOrTimedOut()) {
                curatorClient.getZookeeperClient().getZooKeeper();
            }
        } catch (Exception e) {
            // close the client to release resource
            curatorClient.close();
        }
        return curatorClient;
    }
}
