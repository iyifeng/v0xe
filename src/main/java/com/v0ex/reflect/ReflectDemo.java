package com.v0ex.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yifeng
 * @date 17/9/21
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.v0ex.reflect.Person");
        //实例化对象，没有转型
        Object o = aClass.newInstance();
        //要调用类中的属性
        String attribute = "name";
        Method setMethod = aClass.getMethod("set" + initCap(attribute), String.class);
        Method getMethod = aClass.getMethod("get" + initCap(attribute));
        //相当于person.setName("张三")
        Object invoke = setMethod.invoke(o, "张三");
        //相当于person.getName()
        Object invoke1 = getMethod.invoke(o);
    }

    public static String initCap(String str) {
        return str.substring(0,1).toUpperCase().concat(str.substring(1)) ;
    }
}
