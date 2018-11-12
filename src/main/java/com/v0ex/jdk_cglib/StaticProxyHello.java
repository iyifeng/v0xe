package com.v0ex.jdk_cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yifeng
 * @date 17/9/17
 */
public class StaticProxyHello implements Hello{

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticProxyHello.class);

    private Hello hello = new HelloImpl();
    @Override
    public String sayHello(String name) {
        LOGGER.info("You said:" + name);
        return hello.sayHello(name);
    }
}
