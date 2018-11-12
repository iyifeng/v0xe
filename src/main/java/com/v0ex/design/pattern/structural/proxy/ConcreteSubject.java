package com.v0ex.design.pattern.structural.proxy;

/**
 * 代理模式,属于GoF中结构型模式的一种,
 * 给某一个对象提供一个代理或占位符,并由代理对象来控制对原对象的访问
 *
 * 抽象主题的实现类
 * @author yifeng
 * @date 17/9/17
 */
public class ConcreteSubject extends Subject{

    @Override
    public void doAction() {
        System.out.println("做具体的事情");
    }
}
