package com.v0ex.design.pattern.create.abstractfactory;

/**
 * 抽象工厂模式,属于GoF中创建型模式的一种,
 * 定义一个创建对象的接口,但让实现这个接口的类来决定实例化哪个类,
 * 工厂方法让类的实例化推迟到子类中进行
 *
 * 具体工厂类,生产一系列的产品
 *
 * 备注:这里就实现了一个工厂类,偷懒了,见谅,你可以实现别的具体工厂实现类,
 * 比如再来个具体工厂实现类,能产生狗和玫瑰花
 *
 * @author yifeng
 * @date 17/9/14
 */
public class ConcreteFactory extends Factory{

    @Override
    public ProductZoo createZoo() {
        //创建了猫
        return new ConcreteProductZoo();
    }

    @Override
    public ProductGarden createGarden() {
        //创建了朵昙花
        return new ConcreteProductGarden();
    }
}
