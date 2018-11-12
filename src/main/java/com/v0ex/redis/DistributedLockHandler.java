package com.v0ex.redis;

import redis.clients.jedis.Jedis;

/**
 * @author yifeng
 * @date 17/9/19
 */
public class DistributedLockHandler {

    private static final Integer Lock_Timeout = 3;

    private Jedis jedis;

    /**
     * 外部调用加锁的方法
     * @param lockKey 锁的方法
     * @param timeout 超时时间,（存放时间长度，如5L）
     * @return
     */
    public boolean tryLock(String lockKey, Long timeout){
        try {
            //开始加锁时间
            long currentTime = System.currentTimeMillis();
            boolean result = false;

            while (true){
                //当前时间超过了设定的超时时间
                if ((System.currentTimeMillis() - currentTime)/1000 > timeout){
                    System.out.println("Execute distributedLockHandler.tryLock method,time out ");
                    break;
                }else {
                    result = innerTryLock(lockKey);
                    if (result){
                        break;
                    }else {
                        System.out.println("Try to get the Lock, and wait 100 millisecond ...");
                        Thread.sleep(100);
                    }
                }
            }
            return result;
        }catch (Exception e){
            System.out.println("Failed to run DistributedLockHandler.getLock method." + e);
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey 锁的名字
     */
    public void releaseLock(String lockKey){
        if (!checkIfLockTimeout(System.currentTimeMillis(), lockKey)){
            jedis.del(lockKey);
        }
    }


    /**
     * 内部获取锁的实现方法
     * @param lockKey 锁的名字
     * @return
     */
    private boolean innerTryLock(String lockKey){
        long currentTime = System.currentTimeMillis();
        //锁的持续时间
        String lockTimeDuration = String.valueOf(currentTime + Lock_Timeout + 1);
        Long result = jedis.setnx(lockKey, lockTimeDuration);
        if (result == 1){
            return true;
        }else {
            if (checkIfLockTimeout(currentTime, lockKey)){
                String preLockTimeDuration = jedis.getSet(lockKey, lockTimeDuration);
                if (currentTime > Long.valueOf(preLockTimeDuration)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 判断加锁是否超时
     * @param currentTime
     * @param lockKey
     * @return
     */
    private boolean checkIfLockTimeout(Long currentTime, String lockKey){
        //当前时间超过锁的持续时间
        if (currentTime > Long.valueOf(jedis.get(lockKey))){
            return true;
        }else {
            return false;
        }
    }

    public DistributedLockHandler setJedis(Jedis jedis){
        this.jedis = jedis;
        return this;
    }
}
