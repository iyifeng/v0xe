package com.v0ex.design.pattern.create.commonfactory;


/**
 * 普通工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 具体产品类
 * @author yifeng
 * @date 17/9/14
 */
public class ConcreteProduct implements Product{

    @Override
    public void special() {
        System.out.println("我是bugCoder,这是我的具体方法");
    }
}
