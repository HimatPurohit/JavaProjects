package com.examples;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main -> First");
        // Thread instance created by extending Thread in AnotherThread class
        Thread anotherThread=new AnotherThread();
        anotherThread.setName("==AnotherThread==");
        // .start() executes run method
        anotherThread.start();

        //Anonymous Thread
        // Both of the below methods are valid
        new Thread(){
            @Override
            public void run() {
                System.out.println("Anonymous -> First");
            }
        }.start();
//        Thread anonymousThread=new Thread(){
//            @Override
//            public void run() {
//                System.out.println("Hello from Anonymous Thread");
//            }
//        };
//        anonymousThread.start();


        // Thread created using Runnable Interface
        Thread runnableThread=new Thread(new MyRunnable());
        runnableThread.setName("==MyRunnable==");
        runnableThread.start();

        // Interrupting the thread and not wait for 3 seconds
//        anotherThread.interrupt();

        Thread anonymousRunnableThread=new Thread(new MyRunnable(){
            @Override
            public void run(){
                System.out.println("Anonymous MyRunnable -> First");
                try {
                    // will wait for thread to execute first
//                    anotherThread.join();
                    // will wait for 1000 milliseconds for thread to execute, on timeout will continue execution
                    anotherThread.join(1000);
                    System.out.println("Anonymous MyRunnable terminated, AnotherThread -> second");

                }catch (InterruptedException e){
                    System.out.println("AnotherThread interrupted within Anonymous MyRunnable");
                }
            }
        });
        anonymousRunnableThread.setName("==Anonymous MyRunnable==");
        anonymousRunnableThread.start();

        System.out.println("Main -> Second");
    }
}
