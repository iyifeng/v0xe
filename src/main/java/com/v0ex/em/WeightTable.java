package com.v0ex.em;

/**
 * @author bugcoder
 */
public class WeightTable {

    public static void main(String[] args) {
        double earthWeight = 175;
        double mass = earthWeight / Planet.EARTH.surfaceCravity();
        for (Planet p : Planet.values()){
            System.out.printf("Weight on %s is %f%n",p,p.surfaceWeight(mass));
        }
    }
}
