package com.v0ex.jvm;

/**
 * 相同大小内存分配
 * 使用 JVM 参数-XX:+PrintGCDetails -Xmx20M -Xms20M 运行
 *
 * Created by zbj on 17/5/30.
 */
public class PutInEden {

    public static void main(String[] args) {
        byte[] b1,b2,b3,b4;//定义变量
        b1 = new byte[1024*1024];
        b2 = new byte[1024*1024];
        b3 = new byte[1024*1024];
        b4 = new byte[1024*1024];//分配 1MB 堆空间，考察堆空间的使用情况
    }
}
