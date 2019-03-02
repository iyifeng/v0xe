package com.v0ex.concurrency.practice;

import apple.laf.JRSUIConstants;

/**
 * PrivateLock
 * <p/>
 * Guarding state with a private lock
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrivateLock {
    private final Object myLock = new Object();
    JRSUIConstants.Widget widget;
    void someMethod(){
        synchronized (myLock){
            //Access or modify the state of widget
        }
    }
}
