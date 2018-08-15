package com.v0ex.jvm;

/**
 * -XX:+PrintGCDetails
 *
 * @author zbj
 * @date 17/6/12
 */
public class SlotGc {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64*1024*1024];
        }
        System.gc();
    }
}
