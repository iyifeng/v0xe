package com.v0ex.concurrency.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * Counter
 * <p/>
 * Simple thread-safe counter using the Java monitor pattern
 *
 * @author Brian Goetz and Tim Peierls
 * ThreadSafe
 */
public class PersonSet {
    private final Set<Person> mySet = new HashSet<>();
    public synchronized void addPerson(Person person){
        mySet.add(person);
    }
    public synchronized boolean containsPerson(Person person){
        return mySet.contains(person);
    }
    interface Person{}
}
