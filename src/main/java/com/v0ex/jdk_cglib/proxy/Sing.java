package com.v0ex.jdk_cglib.proxy;

/**
 * 被代理的对象
 *
 * @author yifeng
 * @date 18/9/17
 */
public class Sing implements DoSomething{

    @Override
    public int doSomething(int num) {
        System.out.println("Sing a song");
        return num;
    }
}
