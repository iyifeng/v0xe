package com.v0ex.redis;

import redis.clients.jedis.Jedis;

/**
 * @author bugcoder
 */
public class TestRedisData {

    public static void addData(){
        Jedis jedis  = new Jedis();
        for (int i = 0; i < 10000; i++) {
            jedis.set("key"+i, i+"");
        }
        if (null != jedis){
            jedis.close();
        }
    }

    public static void main(String[] args) {
        addData();
    }
}
