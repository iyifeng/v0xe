package com.v0ex.jdk_cglib.proxy;

/**
 * @author yifeng
 * @date 18/9/17
 */
public class ProxyDemo {

    public static int sing(DoSomething doSomething, int num){
        return doSomething.doSomething(num);
    }

    public static void main(String[] args) {
        int sing = sing(new SingProxy(), 2);
        System.out.println(sing);
    }
}
