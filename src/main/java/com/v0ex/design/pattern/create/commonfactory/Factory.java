package com.v0ex.design.pattern.create.commonfactory;

import com.v0ex.design.pattern.create.simplefactory.Product;

/**
 * 普通工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 缺点:要增加新的产品,需要同时增加具体工厂类,增加了系统的复杂性
 *
 * 抽象工厂类,抽象工厂是工厂方法模式的核心
 * @author yifeng
 * @date 17/9/14
 */
public interface Factory {


    /**
     * 生产产品
     * @return
     */
    public Product create();
}
