package com.v0ex.jdk_cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * http://www.importnew.com/27772.html
 *
 * @author yifeng
 * @date 17/9/17
 */
public class CglibMainTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //指定要代理的目标对象
        enhancer.setSuperclass(CglibConcrete.class);
        //指定实际处理代理逻辑的对象
        enhancer.setCallback(new CglibMethodInterceptor());

        //创建代理对象，对这个对象的所有非final方法的调用都会转发到intercept方法中
        CglibConcrete cglibConcrete = (CglibConcrete) enhancer.create();
        //在intercept方法中，可以加入任何逻辑，如修改方法参数、加入日志和安全检查功能
        cglibConcrete.say("yifeng love python !");

    }
}
