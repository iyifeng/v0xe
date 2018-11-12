package com.v0ex.load.balance;

import java.util.*;

/**
 * 负载均衡:轮询(Round Robin)算法
 * 优点: 试图做到请求转移的绝对均衡
 * 缺点: 为了保证变量pos的互斥性，需要引入重量级的悲观锁synchronized，这会导致该段轮询代码的并发吞吐量发生明显的下降
 * @author yifeng
 * @date 17/10/9
 */
public class RoundRobin {

    private static Integer pos = 0;

    public static String getServer(){
        //重建一个Map,避免服务器的上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);

        //取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos){
            if (pos > keySet.size()){
                pos = 0;
            }
            server = keyList.get(pos);
            pos++;
        }
        return server;
    }
}
