package com.v0ex.em;

/**
 * @author bugcoder
 */
public enum Planet {

    MERCURY(3,2),

    VENUS(4.8,6.05),

    EARTH(5.9,6.3),

    MARS(6.4,3.3);

    /**
     * 质量
     */
    private final double mass;

    /**
     * 半径
     */
    private final double radius;

    /**
     * 表面重力
     */
    private final double surfaceCravity;

    /**
     * 万有引力常量
     */
    private static final double G = 6.67;

    Planet(double mass,double radius){
        this.mass = mass;
        this.radius = radius;
        surfaceCravity = G * mass/(radius * radius);
    }

    public double mass() {
        return mass;
    }

    public double radius(){
        return radius;
    }

    public double surfaceCravity(){
        return surfaceCravity;
    }

    /**
     * 表面重量
     * @param mass
     * @return
     */
    public double surfaceWeight(double mass){
        return mass * surfaceCravity;
    }
}
