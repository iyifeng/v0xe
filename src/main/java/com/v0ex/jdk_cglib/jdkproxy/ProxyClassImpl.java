package com.v0ex.jdk_cglib.jdkproxy;

/**
 * 动态代理-被代理类的实现
 *
 * @author yifeng
 * @date 17/9/17
 */
public class ProxyClassImpl implements IProxyClass{

    @Override
    public int doSomething(int num) {
        System.out.println("方法执行中。。。");
        return num;
    }
}
