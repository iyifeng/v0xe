package com.v0ex.jdk_cglib.jdkproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib
 *
 * @author yifeng
 * @date 17/9/17
 */
public class CglibProxy implements MethodInterceptor{

    private Enhancer enhancer = new Enhancer();

    /**
     *
     * @param o 被代理的对象
     * @param method Method对象用来调用方法
     * @param objects 方法参数
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before " + methodProxy.getSuperName());
        System.out.println(method.getName());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("after " + methodProxy.getSuperName());
        return o1;
    }

    public Object newInstance(Class<?> c){
        //设置产生代理对象的父类
        enhancer.setSuperclass(c);
        //设置Callback接口的实例
        enhancer.setCallback(this);
        //创建目标对象
        Object o = enhancer.create();
        return o;
    }
}
