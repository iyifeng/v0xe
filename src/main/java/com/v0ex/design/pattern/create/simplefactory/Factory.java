package com.v0ex.design.pattern.create.simplefactory;

/**
 * 简单工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 缺点:
 * 1.新添产品,要修改这个类,违背开闭原则
 * 2.静态方法,无法继承
 *
 * 工厂类,生产不同的产品
 * @author yifeng
 * @date 17/9/14
 */
public class Factory {

    protected static final String BUG = "bug";

    protected static final String CODER = "coder";

    /**
     * 静态方法
     * @param arg
     * @return
     */
    public static Product create(String arg){
        Product product = null;
        if (BUG.equals(arg)){
            product = new ConcreteProduct();
        }else if (CODER.equals(arg)){
            //这里是不同的实现类,偷懒了,见谅
            product = new ConcreteProduct();
        }
        return product;
    }
}
