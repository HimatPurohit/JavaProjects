package com.examples;

import java.util.concurrent.locks.ReentrantLock;

public class Starvation {
//    private static Object lock=new Object();
    private static ReentrantLock lock=new ReentrantLock();

    public static void main(String[] args) {
        Thread t1=new Thread(new Worker(ThreadColor.ANSI_RED),"Priority 10");
        Thread t2=new Thread(new Worker(ThreadColor.ANSI_GREEN),"Priority 8");
        Thread t3=new Thread(new Worker(ThreadColor.ANSI_YELLOW),"Priority 6");
        Thread t4=new Thread(new Worker(ThreadColor.ANSI_BLUE),"Priority 4");
        Thread t5=new Thread(new Worker(ThreadColor.ANSI_PURPLE),"Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }

    private static class Worker implements Runnable{
        private int runCount=1;
        private String color;
        public Worker(String color){
            this.color=color;
        }

        @Override
        public void run() {
            for (int i=0;i<100;++i){

                // Only one thread access it till it gets executed completely
                // this raises starvation, i.e. other threads starve till one thread completes task
//                synchronized (lock){
//                    System.out.format(color+ "%s: runcount = %d\n",Thread.currentThread().getName(),runCount++);
//                    // execute critical section of the code here
//                }

                // Using fair locks
                // This gives fair chance to each thread to execute as soon as lock is unlocked
                lock.lock();
                try {
                    System.out.format(color+ "%s: runcount = %d\n",Thread.currentThread().getName(),runCount++);
                    // execute critical section of the code here
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
