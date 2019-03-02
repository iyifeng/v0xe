package com.v0ex.concurrency.practice;

/**
 * MutablePoint
 * <p/>
 * Mutable Point class similar to java.awt.Point
 *
 * @author Brian Goetz and Tim Peierls
 * NotThreadSafe
 */
public class MutablePoint {

    public int x;
    public int y;
    public MutablePoint(){
        x=0;
        y=0;
    }
    public MutablePoint(MutablePoint mp){
        this.x=mp.x;
        this.y=mp.y;
    }
}
