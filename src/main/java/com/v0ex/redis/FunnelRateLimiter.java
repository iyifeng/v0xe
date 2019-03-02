package com.v0ex.redis;

import java.util.HashMap;
import java.util.Map;

/**
 * 漏斗限流
 *
 * @author bugcoder
 */
public class FunnelRateLimiter{
    static class Funnel{
        int capacity;
        float leakingRate;
        int leftQuota;
        long leakingTs;

        public Funnel(int capacity,float leakingRate){
            this.capacity = capacity;
            this.leakingRate = leakingRate;
            this.leftQuota = capacity;
            this.leakingTs = System.currentTimeMillis();
        }

        void makeSpace(){
            long nowTs = System.currentTimeMillis();
            long deltaTs = nowTs - leakingTs;
            int deltaQuata = (int)(deltaTs*leakingRate);
            //时间间隔太长，整个数字过大溢出
            if (deltaQuata < 0) {
                this.leftQuota = capacity;
                this.leakingTs = nowTs;
                return;
            }
            //腾出空间太小，最小单位是1
            if (deltaQuata < 1) {
                return;
            }
            this.leftQuota += deltaQuata;
            this.leakingTs = nowTs;
            if (this.leftQuota > this.capacity) {
                this.leftQuota = this.capacity;
            }
        }

        boolean watering(int quota){
            makeSpace();
            if (this.leftQuota >= quota) {
                this.leftQuota -= quota;
                return true;
            }
            return false;
        }
    }

    private Map<String,Funnel> funnels = new HashMap<>();

    public boolean isActionAllowed(String userId,String actionKey,int capacity,float leakingRate){
        String key = String.format("%s:%s",userId,actionKey);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity,leakingRate);
            funnels.put(key,funnel);
        }
        //需要一个quota
        return funnel.watering(1);
    }


}
