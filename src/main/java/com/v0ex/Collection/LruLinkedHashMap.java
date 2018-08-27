package com.v0ex.Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用LinkedHashMap实现简单的缓存， 必须实现removeEldestEntry方法，具体参见JDK文档
 * @author zbj
 * @date 17/8/21
 */
public class LruLinkedHashMap extends LinkedHashMap{

    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    public LruLinkedHashMap(int maxCapacity) {
        super(maxCapacity,DEFAULT_LOAD_FACTOR,true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxCapacity;
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public Object get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        }finally {
            lock.unlock();
        }

    }

    @Override
    public Object put(Object key, Object value) {
        try {
            lock.lock();
            return super.put(key, value);
        }finally {
            lock.unlock();
        }
    }

    public Collection<Map.Entry> getAll(){
        try {
            lock.lock();
            return new ArrayList<Map.Entry>(super.entrySet());
        }finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        }finally {
            lock.unlock();
        }

    }
}
