package com.v0ex.design.pattern.behavior.observer;

/**
 *
 * 具体观察者,它维护一个指向具体目标对象的引用,它存储具体观察者的有关状态,这些状态需要和具体目标的状态保持一致
 * @author yifeng
 * @date 17/9/14
 */
public class ConcreteObserver implements Observer{

    @Override
    public void update() {
        System.out.println("响应");
    }
}
