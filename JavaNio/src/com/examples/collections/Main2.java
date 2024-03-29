package com.examples.collections;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main2 {
    // Reading and writing data in a group
    public static void main(String[] args) {

//        // Read write Binary Files
//        try (FileOutputStream outputStream = new FileOutputStream("binData.dat");
//             FileChannel binChannel = outputStream.getChannel()) {
//
//            ByteBuffer buffer = ByteBuffer.allocate(100);
//            byte[] outputBytes = "Hello World!".getBytes();
//            buffer.put(outputBytes);
//            long int1Pos = outputBytes.length;
//            buffer.putInt(245);
//            long int2Pos = int1Pos + Integer.BYTES;
//            buffer.putInt(-4245);
//            byte[] outputBytes2 = "nice to meet you.".getBytes();
////            // Multiple inputs by appending .put methods
////            buffer.put(outputBytes2).putInt(1990);
//            buffer.put(outputBytes2);
//            long int3Pos = int2Pos + Integer.BYTES + outputBytes2.length;
//            buffer.putInt(1990);
//
//            buffer.flip();
//            binChannel.write(buffer);
//
////            RandomAccessFile randomAccessFile = new RandomAccessFile("binData.dat", "rwd");
////            FileChannel channel1 = randomAccessFile.getChannel();
////            ByteBuffer readBuffer = ByteBuffer.allocate(100);
////            channel1.read(readBuffer);
////            readBuffer.flip();
////            byte[] inputString = new byte[outputBytes.length];
////            readBuffer.get(inputString);
////            System.out.println("inputString: " + new String(inputString));
////            System.out.println("int1: " + readBuffer.getInt());
////            System.out.println("int2: " + readBuffer.getInt());
////            byte[] inputString2 = new byte[outputBytes2.length];
////            readBuffer.get(inputString2);
////            System.out.println("inputString2: " + new String(inputString2));
////            System.out.println("int3: " + readBuffer.getInt());
//
//
//            // Accessing file based on positions
//            RandomAccessFile randomAccessFile = new RandomAccessFile("binData.dat", "rwd");
//            FileChannel channel1 = randomAccessFile.getChannel();
//            System.out.println("\nReading Files based on positions");
//            ByteBuffer readBuffer = ByteBuffer.allocate(Integer.BYTES);
//            channel1.position(int3Pos);
//            channel1.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int3Pos: " + readBuffer.getInt());
//            readBuffer.flip();
//
//            channel1.position(int2Pos);
//            channel1.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int2Pos: " + readBuffer.getInt());
//            readBuffer.flip();
//
//            channel1.position(int1Pos);
//            channel1.read(readBuffer);
//            readBuffer.flip();
//            System.out.println("int1Pos: " + readBuffer.getInt());
//
//
//            // Copying one file to another
//            RandomAccessFile copyFile=new RandomAccessFile("binDataCopy.dat","rw");
//            FileChannel copyChannel=copyFile.getChannel();
//            channel1.position(0);
////            long numBytestransferred=copyChannel.transferFrom(channel1,0,channel1.size());
//            long numBytestransferred=channel1.transferTo(0,channel1.size(),copyChannel);
//            System.out.println("numBytestransferred: "+ numBytestransferred);
//            channel1.close();
//            randomAccessFile.close();
//            copyChannel.close();
//
//
//            // Writing Data in Random manner
//            byte[] outputString="Hello, World!".getBytes();
//            long str1Pos=0;
//            long newInt1Pos=outputString.length;
//            long newInt2Pos=newInt1Pos+Integer.BYTES;
//            byte[] outputString2="Nice to meet you".getBytes();
//            long str2Pos=newInt2Pos+Integer.BYTES;
//            long newInt3Pos=str2Pos+outputString2.length;
//
//            ByteBuffer intBuffer=ByteBuffer.allocate(Integer.BYTES);
//            intBuffer.putInt(457);
//            intBuffer.flip();
//            binChannel.position(newInt1Pos);
//            binChannel.write(intBuffer);
//            intBuffer.flip();
//
//            intBuffer.putInt(-7457);
//            intBuffer.flip();
//            binChannel.position(newInt2Pos);
//            binChannel.write(intBuffer);
//            intBuffer.flip();
//
//            intBuffer.putInt(4957);
//            intBuffer.flip();
//            binChannel.position(newInt3Pos);
//            binChannel.write(intBuffer);
//            intBuffer.flip();
//
//            binChannel.position(str1Pos);
//            binChannel.write(ByteBuffer.wrap(outputString));
//            binChannel.position(str2Pos);
//            binChannel.write(ByteBuffer.wrap(outputString2));
//
//        } catch (IOException e) {
//            System.out.println("IOException: " + e.getMessage());
//        }

        try {
            Pipe pipe = Pipe.open();
            Runnable writer = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SinkChannel sinkChannel = pipe.sink();
                        ByteBuffer buffer = ByteBuffer.allocate(56);

                        for (int i = 0; i < 10; i++) {
                            String currentTime = "Time: " + System.currentTimeMillis();
                            buffer.put(currentTime.getBytes());
                            buffer.flip();

                            while (buffer.hasRemaining()) {
                                sinkChannel.write(buffer);
                            }
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };


            Runnable reader = new Runnable() {
                @Override
                public void run() {
                    try {
                        Pipe.SourceChannel sourceChannel = pipe.source();
                        ByteBuffer buffer = ByteBuffer.allocate(56);
                        for (int i = 0; i < 10; i++) {
                            int bytesRead = sourceChannel.read(buffer);
                            byte[] timeString = new byte[bytesRead];
                            buffer.flip();
                            buffer.get(timeString);
                            System.out.println("Reader Thread: " + new String(timeString));
                            buffer.flip();
                            Thread.sleep(100);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            new Thread(writer).start();
            new Thread(reader).start();
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
