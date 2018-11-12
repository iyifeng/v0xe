package com.v0ex.jdk_cglib.proxy;

/**
 * 代理对象，它来代理Sing
 *
 * @author yifeng
 * @date 18/9/17
 */
public class SingProxy implements DoSomething{

    private DoSomething sing = new Sing();

    @Override
    public int doSomething(int num) {
        System.out.println("before sing");
        int i = sing.doSomething(num);
        System.out.println("after sing");
        return i;
    }
}
