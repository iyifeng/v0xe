package com.v0ex.jdk_cglib;

/**
 * CGLIB,没有实现接口也就无法使用JDK动态代理，这时候cglib登场
 * @author yifeng
 * @date 17/9/17
 */
public class CglibConcrete {

    public String say(String name){
        return "CglibConcrete" + name;
    }
}
