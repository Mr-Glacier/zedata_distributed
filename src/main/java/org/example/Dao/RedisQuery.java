package org.example.Dao;

import org.example.Until.JedisDBPool;
import redis.clients.jedis.Jedis;

public class RedisQuery {


    /**
     * 添加数据,相同key会覆盖数据，所以也相当于改
     */
    public static void set(String key, String value) {
        Jedis jedis = JedisDBPool.getConnectJedis();
        jedis.set(key, value);
        //Jedis3.0版本后jedisPool.returnResource(final Jedis resource)方法已被弃用，用jedis.close()代替 来归还连接
        jedis.close();
    }


    /**
     * 添加数据，带超时时间，超时自动销毁
     */
    public static void setex(String key, String value, int seconds){
        Jedis jedis = JedisDBPool.getConnectJedis();
        jedis.setex(key, seconds, value);
        jedis.close();
    }

    /**
     * 根据key删除数据
     */
    public static void deleteByKey(String key) {
        Jedis jedis = JedisDBPool.getConnectJedis();
        jedis.del(key);
        jedis.close();
    }

    /**
     * 根据key查询
     */
    public static String getByKey(String key) {
        Jedis jedis = JedisDBPool.getConnectJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }

    /**
     * 查询某条数据是否存在
     */
    public static boolean isExist(String key){
        Jedis jedis = JedisDBPool.getConnectJedis();
        boolean exist = jedis.exists(key);
        jedis.close();
        return exist;
    }

    public static void main(String[] args) {
        try {
//            set("测试key","测试value");
            System.out.println(getByKey("测试key"));
        }catch (Exception e){
            e.printStackTrace();
        }
        //通过给key添加冒号可以使数据在Redis Desktop manager中以文件夹的形式展示
//        set("User:测试key","测试value");
    }

}
