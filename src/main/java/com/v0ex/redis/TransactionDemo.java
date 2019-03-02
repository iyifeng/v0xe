package com.v0ex.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * @author bugcoder
 */
public class TransactionDemo {

    public static void main(String[] args){
        Jedis jedis = new Jedis();
        String userId = "abc";
        String key = keyFor(userId);
        //setnx 做初始化
        jedis.setnx(key,String.valueOf(5));
        System.out.println(doubleAccount(jedis,userId));
        jedis.close();
    }

    public static int doubleAccount(Jedis jedis,String userId){
        String key = keyFor(userId);
        while(true){
            jedis.watch(key);
            int value = Integer.parseInt(jedis.get(key));
            //加倍
            value *= 2;
            Transaction tx = jedis.multi();
            tx.set(key,String.valueOf(value));
            List<Object> res = tx.exec();
            if (res != null) {
                //非空表示成功
                break;
            }
        }
        //重新获取余额
        return Integer.parseInt(jedis.get(key));
    }

    public static String keyFor(String userId){
        return String.format("account_%s",userId);
    }
}
