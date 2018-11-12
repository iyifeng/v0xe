package com.v0ex.load.balance;

import java.util.*;

/**
 * 负载均衡算法：加权轮询算法，不同的服务器可能机器配置和当前系统的负载并不相同，因此它们的抗压能力也不尽相同，给配置高、负载低的机器配置更高的权重，让其处理更多的请求
 * 与轮询法类似，只是在获取服务器地址之前增加了一段权重计算的代码，根据权重的大小，将地址重复地增加到服务器地址列表中，权重越大，该服务器每轮所获得的请求数量越多。
 * @author yifeng
 * @date 17/10/9
 */
public class WeightRoundRobin {

    private static Integer pos ;

    public static String getString(){
        //重建一个Map,避免服务器的上下线导致的并发问题
        Map<String,Integer> serverMap = new HashMap<>();
        serverMap.putAll(IpMap.serverWeightMap);
        //取得Ip地址列表
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<>();
        while (iterator.hasNext()){
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) {
                serverList.add(server);
            }
        }
        String server = null;
        synchronized (pos){
            if (pos > serverList.size()){
                pos = 0;
            }
            server = serverList.get(pos);
            pos++;
        }
        return server;
    }
}
