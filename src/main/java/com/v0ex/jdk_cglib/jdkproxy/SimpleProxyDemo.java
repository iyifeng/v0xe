package com.v0ex.jdk_cglib.jdkproxy;

/**
 * @author yifeng
 * @date 17/9/17
 */
public class SimpleProxyDemo {

    public static void main(String[] args) {
        ProxyClassImpl proxyClass = new ProxyClassImpl();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(proxyClass);
        IProxyClass iProxyClass = (IProxyClass)dynamicProxyHandler.newProxyInstance();
        //System.out.println(iProxyClass.getClass().getName());
        System.out.println(iProxyClass.doSomething(4));
    }
}
