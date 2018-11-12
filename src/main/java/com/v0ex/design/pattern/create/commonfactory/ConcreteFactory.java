package com.v0ex.design.pattern.create.commonfactory;

import com.v0ex.design.pattern.create.simplefactory.ConcreteProduct;
import com.v0ex.design.pattern.create.simplefactory.Product;

/**
 * 简单工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 具体工厂类
 * @author yifeng
 * @date 17/9/14
 */
public class ConcreteFactory implements Factory{

    /**
     * 创建产品,这里生产的是ConcreteProduct,
     * 类似别的具体工厂会生产ConcreteProduct1,2等等
     * @return
     */
    @Override
    public Product create() {
        return new ConcreteProduct();
    }
}
