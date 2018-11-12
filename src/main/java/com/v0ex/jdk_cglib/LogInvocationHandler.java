package com.v0ex.jdk_cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * JDK动态代理是基于接口的，如果对象没有实现接口，那就无法使用JDK动态代理
 *
 * @author yifeng
 * @date 17/9/17
 */
public class LogInvocationHandler implements InvocationHandler{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogInvocationHandler.class);

    private Hello hello;

    public LogInvocationHandler(Hello hello) {
        this.hello = hello;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sayHello".equals(method.getName())){
            LOGGER.info("JDKINVOKE said: " + Arrays.toString(args));
        }
        return method.invoke(hello,args);
    }
}
