package com.v0ex.jdk_cglib.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理-InvocationHandler接口实现
 *
 * @author yifeng
 * @date 17/9/17
 */
public class DynamicProxyHandler implements InvocationHandler{

    private Object proxied;

    /**
     *
     * @param proxied 被代理的对象
     */
    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    /**
     * 返回代理对象
     * @return
     */
    public Object newProxyInstance(){
        System.out.println("这个对象到底是"+proxied.getClass().getName());
        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(),
                proxied.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        addToDisk(proxy.getClass().getName(),ProxyClassImpl.class,"/Users/zbj/Downloads/$Proxy0.class");
        System.out.println(method.getName());
        System.out.println("args: " + args[0].getClass().getName());
        System.out.println("Before invoke method ...");
        Object object = method.invoke(proxied, args);
        System.out.println("After invoke method ...");
        return object;
    }

    private void addToDisk(String className, Class<?> cl, String path){
        byte[] classFile = ProxyGenerator.generateProxyClass(className, cl.getInterfaces());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        }catch ( IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
