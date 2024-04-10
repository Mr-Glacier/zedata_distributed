package org.example.Until;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JedisDBPool {

    public static JedisPool jedisPool;

    static {
        try {
            Properties properties = new Properties();
            InputStream in = CommonUtils.readResourceFile("redis.properties");
            properties.load(in);
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
            jedisPoolConfig.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
            jedisPoolConfig.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
            jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(properties.getProperty("maxWaitMillis")));
            jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("testOnBorrow")));
            jedisPoolConfig.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("testOnReturn")));
            jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Integer.parseInt(properties.getProperty("timeBetweenEvictionRunsMillis")));
            jedisPoolConfig.setMinEvictableIdleTimeMillis(Integer.parseInt(properties.getProperty("minEvictableIdleTimeMillis")));
            jedisPoolConfig.setNumTestsPerEvictionRun(Integer.parseInt(properties.getProperty("numTestsPerEvictionRun")));

//            jedisPool = new JedisPool(jedisPoolConfig,properties.getProperty("host"),Integer.parseInt(properties.getProperty("port")));
            jedisPool = new JedisPool(jedisPoolConfig, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")), 2000, "151300");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Jedis getConnectJedis() {
        return jedisPool.getResource();
    }
}
