package com.v0ex.load.balance;

import java.util.*;
import java.util.Random;

/**
 * 负载均衡算法：加权随机算法，与加权轮询法类似，也是根据后端服务器不同的配置和负载情况来配置不同的权重。不同的是，它是按照权重来随机选择服务器的，而不是顺序
 * @author yifeng
 * @date 17/10/9
 */
public class WeightRandom {

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
        Random random = new Random();
        int pos = random.nextInt(serverList.size());
        return serverList.get(pos);
    }
}
