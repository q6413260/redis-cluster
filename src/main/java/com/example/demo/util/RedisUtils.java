package com.example.demo.util;

import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xiaoming on 15/06/2017.
 */
public class RedisUtils {
    public static Set<HostAndPort> parseAddress(String serverAddress){
        String[] addressArray = serverAddress.split(",");
        Set<HostAndPort> set = new HashSet<>();
        for (String address : addressArray) {
            String host = address.split(":")[0];
            int port = Integer.parseInt(address.split(":")[1]);
            HostAndPort hostAndPort = new HostAndPort(host, port);
            set.add(hostAndPort);
        }
        return set;
    }
}
