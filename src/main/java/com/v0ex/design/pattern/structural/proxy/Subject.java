package com.v0ex.design.pattern.structural.proxy;

/**
 * 代理模式,属于GoF中结构型模式的一种,
 * 给某一个对象提供一个代理或占位符,并由代理对象来控制对原对象的访问
 *
 * 抽象主题类,可以是接口,抽象类或实现类
 * 客户端针对抽象主题类编程
 * @author yifeng
 * @date 17/9/17
 */
public abstract class Subject {

    public abstract void doAction();
}
