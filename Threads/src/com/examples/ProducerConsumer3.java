package com.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.examples.ProducerConsumer3.EOF;

public class ProducerConsumer3 {
    // using java.util.concurrent

    public static final String EOF = "EOF";

    public static void main(String[] args) {
//        List<String> buffer = new ArrayList<>();
//        ReentrantLock bufferLock = new ReentrantLock();

        // ArrayBlockingQueue doesn't require ReentrantLock lock and unlock method
        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(10);

        // Creating Thread Pool, i.e only a particular number of threads will execute at a time using ExecutorService
        // Maximun 3 threads will run simultaneously
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Maximun 5 threads will run simultaneously
        ExecutorService executorService = Executors.newFixedThreadPool(5);


//        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
//        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_YELLOW, bufferLock);
//        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE, bufferLock);

        // Using ArrayBlockingQueue
        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_BLUE);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);

//        new Thread(producer).start();
//        new Thread(consumer1).start();
//        new Thread(consumer2).start();


        // Executing threads using ExecutorService, it doesn't return any result
        executorService.execute(producer);
        executorService.execute(consumer1);
        executorService.execute(consumer2);

        // Receive value back from a thread, returned in form of Future object,
        // submit method is used to return a callable object similar to runnable object.
        // Cannot be used with Runnable class
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColor.ANSI_WHITE + "This is within callable Anonymous class");
                return "This is the Callable result";
            }
        });

        try {
            System.out.println(future.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("The thread execution was interrupted");
        }


        // Always need to close running ExecutorService after the end of execution
        executorService.shutdown();
    }
}

class MyProducer implements Runnable {
    //    private List<String> buffer;
    private ArrayBlockingQueue<String> buffer;
    private String color;

    //    private ReentrantLock bufferLock;
//public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
//        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);
//                bufferLock.lock();
                buffer.put(num);
//                try {
//                    buffer.add(num);
//                } finally {
//                    bufferLock.unlock();
//                }
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was Interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting...");
//        bufferLock.lock();
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {

        }
//        try {
//            buffer.add("EOF");
//        } finally {
//            bufferLock.unlock();
//        }
    }
}

class MyConsumer implements Runnable {
    //    private List<String> buffer;
    private ArrayBlockingQueue<String> buffer;
    private String color;
//    private ReentrantLock bufferLock;

    //    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
//        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        int counter = 0;

        while (true) {
//            bufferLock.lock();
//                try {
//                    if (buffer.isEmpty()) {
//                        continue;
//                    }
//                    if (buffer.get(0).equals(EOF)) {
//                        System.out.println(color + "Exiting");
//                        break;
//                    } else {
//                        System.out.println(color + "Removed..." + buffer.remove(0));
//                    }
//                }finally {
//                    bufferLock.unlock();
//                }

            // locks only when lock is unlocked
//            if (bufferLock.tryLock()) {
//                try {
//                    if (buffer.isEmpty()) {
//                        continue;
//                    }
//                    System.out.println("counter: "+counter);
//                    counter=0;
//                    if (buffer.get(0).equals(EOF)) {
//                        System.out.println(color + "Exiting");
//                        break;
//                    } else {
//                        System.out.println(color + "Removed..." + buffer.remove(0));
//                    }
//                } finally {
//                    bufferLock.unlock();
//                }
//            } else{
//            counter++;
//        }


            synchronized (buffer){
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed..." + buffer.take());
                    }
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
