package com.v0ex.concurrency.practice;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 *
 * @author Brian Goetz and Tim Peierls
 */
public class NoVisibility {

    private static boolean ready;

    private static int number;

    public static class ReadyThread extends Thread{
        public void run(){
            while (!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReadyThread().start();
        number = 42;
        ready = true;
    }
}
