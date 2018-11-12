package com.v0ex.jdk_cglib;

import java.lang.reflect.Proxy;

/**
 * @author yifeng
 * @date 17/9/17
 */
public class JdkMainTest {

    public static void main(String[] args) {
        Hello hello = (Hello)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{Hello.class},
                new LogInvocationHandler(new HelloImpl()));
        hello.sayHello("yifeng love java");
    }
}
