package com.v0ex.redis;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author bugcoder
 */
public class LRUCache {

    private LinkedHashMap<Integer,Integer> map;

    private final int CAPACITY;

    public LRUCache(int capacity){
        CAPACITY = capacity;
        map = new LinkedHashMap<Integer, Integer>(capacity,0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > CAPACITY;
            }
        };
    }

    public int get(int key){
        return map.getOrDefault(key,-1);
    }

    public void put(int key,int value){
        map.put(key,value);
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        int i = cache.get(1);
        System.out.println("i="+i);
        cache.put(3,3);
        int j = cache.get(2);
        System.out.println("j="+j);
        cache.put(4,4);
        int k = cache.get(1);
        System.out.println("k="+k);
        int m = cache.get(3);
        System.out.println("m="+m);
        int n = cache.get(4);
        System.out.println("n="+n);
    }
 }
