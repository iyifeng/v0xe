package com.v0ex.design.pattern.create.abstractfactory;

/**
 * 抽象工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 抽象工厂类,可以是接口,也可以是抽象类和具体类,它声明一组创建一个系列产品方法
 * @author yifeng
 * @date 17/9/14
 */
public abstract class Factory {

    /**
     * 来个动物园
     * @return
     */
    public abstract ProductZoo createZoo();

    /**
     * 来个花园
     * @return
     */
    public abstract ProductGarden createGarden();
}
