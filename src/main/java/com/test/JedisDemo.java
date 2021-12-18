package com.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisDemo {
    public static void main(String[] args) {

        //连接数据库
//        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);

        //创建连接池对象
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);

        //获得连接信息
        Jedis jedis = jedisPool.getResource();

        //设置内容
//        jedis.set("test", "hello Redis");

        System.out.println(jedis.get("test"));
        //关闭资源
        jedis.close();

    }
}
