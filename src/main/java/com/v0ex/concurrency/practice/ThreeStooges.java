package com.v0ex.concurrency.practice;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * ThreeStooges
 * <p/>
 * Immutable class built out of mutable underlying objects,
 * demonstration of candidate for lock elision
 *
 * @author Brian Goetz and Tim Peierls
 */
public final class ThreeStooges {

    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges(){
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooges(String name){
        return stooges.contains(name);
    }

    public String getStoogesNames(){
        List<String> stooges = new Vector<>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
