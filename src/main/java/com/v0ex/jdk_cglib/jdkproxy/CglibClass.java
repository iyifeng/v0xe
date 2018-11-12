package com.v0ex.jdk_cglib.jdkproxy;

/**
 * @author yifeng
 * @date 17/9/17
 */
public class CglibClass {

    public int doSomething(int num){
        System.out.println("方法执行中。。。");
        return num;
    }
}
