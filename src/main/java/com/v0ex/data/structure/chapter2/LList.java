package com.v0ex.data.structure.chapter2;

/**
 * Created by zbj on 17/4/2.
 */
public interface LList<T> {

    boolean isEmpty();//判空

    int length();//求长

    T get(int i);//返回第i个元素

    void set(int i,T x);//设置第i个元素为x

    void insert(int i,T x);//插入x作为第i个元素

    void append(T x);//在线性表最后插入x元素

    T remove(int i);//删除并返回对象

    void removeAll();//删除所有

    T search(T key);//返回首次出现关键字为key的元素
}
