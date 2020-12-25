package com.examples;

public class Counter {
    public static void main(String[] args) {
        CountDown countDown=new CountDown();

        // Multiple threads of same class instance
        // Threads Share heap memory for same instance and does not share thread stack
        CountDownThread thread1=new CountDownThread(countDown);
        thread1.setName("Thread 1");
        CountDownThread thread2=new CountDownThread(countDown);
        thread2.setName("Thread 2");
        thread1.start();
        thread2.start();


        // Multiple threads of different class instance
        CountDownThread thread3=new CountDownThread(new CountDown());
        thread3.setName("Thread 3");
        CountDownThread thread4=new CountDownThread(new CountDown());
        thread4.setName("Thread 4");
        thread3.start();
        thread4.start();
    }
}

class CountDown{

    // uses unique values for any number of threads
//    public void doCountdown(){
//        String color;
//        switch (Thread.currentThread().getName()){
//            case "Thread 1":
//                color=ThreadColor.ANSI_CYAN;
//                break;
//            case "Thread 2":
//                color=ThreadColor.ANSI_PURPLE;
//                break;
//            default:
//                color=ThreadColor.ANSI_RED;
//        }
//        for (int i=10;i>0;--i) {
//            System.out.println(color+Thread.currentThread().getName()+" : "+i);
//        }
//    }

    // all threads will refer the value of i for same instance of the class and not unique values
//    private int i;
//    public void doCountdown(){
//        String color;
//        switch (Thread.currentThread().getName()){
//            case "Thread 1":
//                color=ThreadColor.ANSI_CYAN;
//                break;
//            case "Thread 2":
//                color=ThreadColor.ANSI_PURPLE;
//                break;
//            case "Thread 3":
//                color=ThreadColor.ANSI_YELLOW;
//                break;
//            case "Thread 4":
//                color=ThreadColor.ANSI_CYAN;
//                break;
//            default:
//                color=ThreadColor.ANSI_RED;
//        }
//        for (i=10;i>0;--i) {
//            System.out.println(color+Thread.currentThread().getName()+" : "+i);
//        }
//    }


    // Synchronizing the method using synchronized
    // only single threads will access the method at a time for the same instance
//    private int i;
//    public synchronized void doCountdown(){
//        String color;
//        switch (Thread.currentThread().getName()){
//            case "Thread 1":
//                color=ThreadColor.ANSI_CYAN;
//                break;
//            case "Thread 2":
//                color=ThreadColor.ANSI_PURPLE;
//                break;
//            case "Thread 3":
//                color=ThreadColor.ANSI_YELLOW;
//                break;
//            case "Thread 4":
//                color=ThreadColor.ANSI_BLUE;
//                break;
//            default:
//                color=ThreadColor.ANSI_RED;
//        }
//        for (i=10;i>0;--i) {
//            System.out.println(color+Thread.currentThread().getName()+" : "+i);
//        }
//    }


    // Synchronizing the loop using synchronized method with this keyword i.e. common reference for the threads
    // only single threads will access the for loop at a time for the same instance
    private int i;
    public void doCountdown(){
        String color;
        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color=ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color=ThreadColor.ANSI_PURPLE;
                break;
            case "Thread 3":
                color=ThreadColor.ANSI_YELLOW;
                break;
            case "Thread 4":
                color=ThreadColor.ANSI_BLUE;
                break;
            default:
                color=ThreadColor.ANSI_RED;
        }
        synchronized (this){
            for (i=10;i>0;--i) {
                System.out.println(color+Thread.currentThread().getName()+" : "+i);
            }
        }
    }
}

class CountDownThread extends Thread{
    private CountDown threadCountDown;
    public CountDownThread(CountDown countDown){
        threadCountDown=countDown;
    }
    public void run(){
        threadCountDown.doCountdown();
    }
}
