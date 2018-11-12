package com.v0ex.design.pattern.behavior.observer;

/**
 *
 * 具体目标类,它包含有经常发生改变的数据,当它的状态发生改变时,向它的各个观察者发出通知
 * @author yifeng
 * @date 17/9/14
 */
public class ConcreteSubject extends Subject{

    @Override
    public void notification() {
        System.out.println("通知各个观察者");
        for (Observer observer: observers) {
            observer.update();
        }
    }
}
