package com.v0ex.jvm;

/**
 * 创建一个大对象
 * 使用 JVM 参数-XX:+PrintGCDetails –Xmx20M –Xms20MB 运行
 * 如果需要将 1MB 以上的对象直接在年老代分配，设置-XX:PetenureSizeThreshold=1000000
 * Created by zbj on 17/5/30.
 */
public class BigObj2Old {

    public static void main(String[] args) {
        byte[] b;
        b = new byte[1024*1024];//分配一个 1MB 的对象
    }
}
