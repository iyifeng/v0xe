package com.v0ex.design.pattern.behavior.observer;

/**
 * 观察者模式,属于GoF中行为型模式的一种,一个目标对象管理所有相依于它的观察者对象,
 * 并且在它本身的状态改变时主动发出通知.这通常透过呼叫各观察者所提供的方法来实现.
 * 此种模式通常被用来实时事件处理系统.
 *
 * 观察者，一般定义为接口
 * @author yifeng
 * @date 17/9/14
 */
public interface Observer {

    /**
     * 声明响应方法
     */
    public void update();
}
