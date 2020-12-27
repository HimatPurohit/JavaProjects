package com.examples.LiveLocks;

public class Main {
    // Reference for Live Lock

    public static void main(String[] args) {
        final Worker worker1=new Worker("Worker 1",true);
        final Worker worker2=new Worker("Worker 2",true);

        final SharedResources sharedResource=new SharedResources(worker1);

        // Both workers ask for resources at the same time

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker1.work(sharedResource,worker2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                worker2.work(sharedResource,worker1);
            }
        }).start();
    }
}
