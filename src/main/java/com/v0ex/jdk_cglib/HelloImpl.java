package com.v0ex.jdk_cglib;

/**
 * @author yifeng
 * @date 17/9/17
 */
public class HelloImpl implements Hello{
    @Override
    public String sayHello(String name) {
        return "HelloImpl" + name ;
    }
}
