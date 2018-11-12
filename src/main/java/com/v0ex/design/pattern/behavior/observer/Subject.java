package com.v0ex.design.pattern.behavior.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 观察者模式,属于GoF中行为型模式的一种,一个目标对象管理所有相依于它的观察者对象,
 * 并且在它本身的状态改变时主动发出通知.这通常透过呼叫各观察者所提供的方法来实现.
 * 此种模式通常被用来实时事件处理系统.
 *
 * 目标对象,可以是接口,也可以是抽象类或具体类
 * @author yifeng
 * @date 17/9/14
 */
public abstract class Subject {

    /**
     * 观察者集合，用来发送通知
     */
    protected List<Observer> observers = Collections.synchronizedList(new ArrayList<>());

    /**
     * 添附(Attach):新增观察者,以追踪目标对象的变化.
     * @param observer
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * 解附(Detach):将已经存在的观察者移除
     * @param observer
     */
    public void detach(Observer observer){
        observers.remove(observer);
    }

    /**
     * 通知(Notify):利用观察者所提供的更新方法来通知此目标已经产生变化
     */
    public abstract void notification();
}
