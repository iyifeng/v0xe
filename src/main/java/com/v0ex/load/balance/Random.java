package com.v0ex.load.balance;

import java.util.*;

/**
 * 负载均衡算法: Random(随机法),通过系统随机函数，根据后端服务器列表的大小值来随机选择其中一台进行访问。
 * 基于概率统计的理论，吞吐量越大，随机算法的效果越接近于轮询算法的效果。
 * @author yifeng
 * @date 17/10/9
 */
public class Random {

    public static String getServer(){
        //重建一个Map,避免服务器的上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);
        //取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        keyList.addAll(keySet);

        java.util.Random random = new java.util.Random();
        int pos = random.nextInt(keyList.size());
        String server = keyList.get(pos);
        return server;
    }
}
