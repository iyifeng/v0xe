package com.v0ex.Collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zbj on 18/2/27.
 */
public class ConcurrentHashMapVSSynchronizedMap {

    public static final int THREAD_POOL_SIZE = 5;

    public static Map<String,Integer> hashTableObject = null;

    public static Map<String,Integer> synchronizedMapObject = null;

    public static Map<String,Integer> concurrentHashMapObject = null;

    public static void vsTest(Map<String,Integer> threads) throws InterruptedException {
        System.out.println("Test started for:" + threads.getClass());
        long averageTime = 0;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int k = 0; k < 500000; k++) {
                            Integer randomNum = (int) Math.ceil(Math.random() * 550000);
                            Integer randomValue = threads.get(String.valueOf(randomNum));
                            threads.put(String.valueOf(randomNum),randomNum);
                        }
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
            long endTime = System.nanoTime();
            long totalTime = (endTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println("2500K entried added/retrieved in " + totalTime +"ms");
        }
        System.out.println("For" + threads.getClass() + "the average time is " + averageTime/5 + "ms\n");
    }

    public static void main(String[] args) throws InterruptedException {
        hashTableObject = new Hashtable<>();
        vsTest(hashTableObject);

        synchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
        vsTest(synchronizedMapObject);

        concurrentHashMapObject = new ConcurrentHashMap<>();
        vsTest(concurrentHashMapObject);
    }
}
