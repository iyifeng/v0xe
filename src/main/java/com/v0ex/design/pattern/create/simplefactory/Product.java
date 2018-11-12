package com.v0ex.design.pattern.create.simplefactory;

/**
 * 简单工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 抽象产品类,可以是接口,也可以是抽象类
 * @author yifeng
 * @date 17/9/14
 */
public abstract class Product {

    /**
     * 具体产品类的公共方法
     */
    public void common(){
        System.out.println("这是公共方法");
    }

    /**
     * 各个具体产品类的独特的方法
     */
    public abstract void special();
}
