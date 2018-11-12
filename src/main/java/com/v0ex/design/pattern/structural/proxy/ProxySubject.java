package com.v0ex.design.pattern.structural.proxy;

/**
 * 代理模式,属于GoF中结构型模式的一种,
 * 给某一个对象提供一个代理或占位符,并由代理对象来控制对原对象的访问
 *
 * 代理类,是代理模式的精髓所在,它也是抽象主题类的实现类
 * 它包含了对真实主题实现类的引用
 * @author yifeng
 * @date 17/9/17
 */
public class ProxySubject extends Subject{

    private Subject subject = new ConcreteSubject();

    /**
     * 在原有方法的基础上,再添加些新的方法对其功能进行扩充或约束
     */
    @Override
    public void doAction() {
        System.out.println("before ,在方法执行前搞些事情");
        subject.doAction();
        System.out.println("after, 执行完再搞些事情");
    }
}
