package com.v0ex.jdk_cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 实现一个MethodInterceptor,方法调用会被转发到该类的intercept()方法
 *
 * @author yifeng
 * @date 17/9/17
 */
public class CglibMethodInterceptor implements MethodInterceptor{

    private static final Logger LOGGER = LoggerFactory.getLogger(CglibMethodInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        LOGGER.info("CGLIB said: " + Arrays.toString(objects));
        //methodProxy的invokeSuper方法会将调用转发到原始对象
        return methodProxy.invokeSuper(o,objects);
    }
}
