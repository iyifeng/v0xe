package com.v0ex.design.pattern.create.abstractfactory;

/**
 * 抽象工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 抽象产品类,接口实现,声明了一个系列的产品,比如该抽象产品声明了动物园,一系列的动物
 * @author yifeng
 * @date 17/9/14
 */
public interface ProductZoo {

    /**
     * 各个具体产品类的独特的方法
     */
    public abstract void special();
}
