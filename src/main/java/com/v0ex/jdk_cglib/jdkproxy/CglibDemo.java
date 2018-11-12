package com.v0ex.jdk_cglib.jdkproxy;

/**
 * https://www.jianshu.com/p/e2917b0b9614
 *
 * @author yifeng
 * @date 17/9/17
 */
public class CglibDemo {

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        CglibClass cglibClass = (CglibClass) cglibProxy.newInstance(CglibClass.class);
        System.out.println(cglibClass.doSomething(5));
    }
}
