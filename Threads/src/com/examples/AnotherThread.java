package com.examples;

public class AnotherThread extends Thread{
    @Override
    public void run() {
        System.out.println(currentThread().getName()+" AnotherThread -> First");
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Another Thread woke me up");
            // stops further execution when thread is interrupted
            return;
        }
        System.out.println(currentThread().getName()+" AnotherThread -> second After 3 seconds");
    }
}
