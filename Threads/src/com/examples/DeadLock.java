package com.examples;

import static com.examples.DeadLock.lock1;

public class DeadLock {
    // To avoid deadlock use only one lock/object but it is not possible for many applications
    // Use the locks in the same order which will prevent deadlock


    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1: has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread1: waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread1: has lock1 and lock2");
                }
                System.out.println("Thread1: Released lock2");
            }
            System.out.println("Thread1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread2: has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread2: waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread2: has lock1 and lock2");
                }
                System.out.println("Thread2: Released lock2");
            }
            System.out.println("Thread1: Released lock1. Exiting...");
        }
    }
}