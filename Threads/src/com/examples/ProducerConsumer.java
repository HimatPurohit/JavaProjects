package com.examples;

import java.util.Random;

public class ProducerConsumer {
    public static void main(String[] args) {
        // Consumer consumes and Producer produces
        // Only produced can be consumed
        Message message=new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message{
    private String message;
    private boolean empty=true;

    public synchronized String read(){
        while (empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty=true;
        notifyAll();
        return message;
    }

    public synchronized void write(String message){
        while (!empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty=false;
        this.message=message;
        notifyAll();
    }
}


class Writer implements Runnable{
    private Message message;
    public  Writer(Message message){
        this.message=message;
    }

    @Override
    public void run() {
        String[] messages={"message1","message2","message3","message4","message5","message6","message7","message8"};
        Random random=new Random();
        for (int i=0;i< messages.length;i++){
            System.out.println("Writing: "+messages[i]);
            message.write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                System.out.println("");
            }
        }
        message.write("Finished");
    }
}


class Reader implements Runnable{
    private Message message;
    public  Reader(Message message){
        this.message=message;
    }

    @Override
    public void run() {
        Random random=new Random();
        for (String latestMessage=message.read(); !latestMessage.equals("Finished");latestMessage=message.read()){
            System.out.println("Reading: "+latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                System.out.println("");
            }
        }
    }
}