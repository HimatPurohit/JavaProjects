package com.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.examples.ProducerConsumer2.EOF;

public class ProducerConsumer2 {
    // using synchronized methods

    public static final String EOF = "EOF";

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        MyProducer2 producer = new MyProducer2(buffer, ThreadColor.ANSI_BLUE);
        MyConsumer2 consumer1 = new MyConsumer2(buffer, ThreadColor.ANSI_YELLOW);
        MyConsumer2 consumer2 = new MyConsumer2(buffer, ThreadColor.ANSI_PURPLE);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();
    }
}

class MyProducer2 implements Runnable {
    private List<String> buffer;
    private String color;

    public MyProducer2(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (String num : nums) {
            try {
                System.out.println(color + "Adding..." + num);
                synchronized (buffer) {
                    buffer.add(num);
                }
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was Interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting...");
        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}

class MyConsumer2 implements Runnable {
    private List<String> buffer;
    private String color;

    public MyConsumer2(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals(EOF)) {
                    System.out.println(color + "Exiting");
                    break;
                } else {
                    System.out.println(color + "Removed..." + buffer.remove(0));
                }
            }
        }
    }
}
