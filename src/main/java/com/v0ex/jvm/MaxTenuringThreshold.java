package com.v0ex.jvm;

/**
 *  申请内存
 *  参数设置为：-XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=2
 *  更改参数为-XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn10M -XX:SurvivorRatio=2 -XX:MaxTenuringThreshold=1
 *
 * Created by zbj on 17/5/30.
 */
public class MaxTenuringThreshold {

    public static void main(String[] args) {
        byte[] b1,b2,b3;
        b1 = new byte[1024*512];
        b2 = new byte[1024*1024*2];
        b3 = new byte[1024*1024*4];
        b3 = null;
        b3 = new byte[1024*1024*4];
    }
}
