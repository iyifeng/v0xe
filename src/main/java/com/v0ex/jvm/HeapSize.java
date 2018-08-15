package com.v0ex.jvm;

import java.util.Vector;

/**
 * 堆大小设置
 * 测试-XX:MinHeapFreeRatio 和-XX:MaxHeapFreeRatio 的作用，
 * 设置运行参数为-XX:+PrintGCDetails -Xms10M -Xmx40M -XX:MinHeapFreeRatio=40 -XX:MaxHeapFreeRatio=50
 *  改用参数：-XX:+PrintGCDetails -Xms40M -Xmx40M -XX:MinHeapFreeRatio=40 -XX:MaxHeapFreeRatio=50
 * Created by zbj on 17/5/30.
 */
public class HeapSize {

    public static void main(String[] args) throws InterruptedException {
        Vector vector = new Vector();
        while (true){
            byte[] bytes = new byte[1024*1024];
            vector.add(bytes);
            if (vector.size()==10){
                vector = new Vector();
                break;
            }
            Thread.sleep(100);
        }
    }
}
