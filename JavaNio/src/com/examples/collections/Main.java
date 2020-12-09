package com.examples.collections;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {
// Reading and writing individual data
    public static void main(String[] args) {
//        try {
////        FileInputStream inputStream=new FileInputStream("data.txt");
////        FileChannel channel=inputStream.getChannel();
//        Path dataPath= FileSystems.getDefault().getPath("data.txt");
//        // Different ways of appending data to the file
//        Files.write(dataPath,"\nLine 4".getBytes("UTF-8"), StandardOpenOption.APPEND);
//        Files.write(dataPath,"\nLine 5".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
//        Files.writeString(dataPath, "\nLine 6", StandardOpenOption.APPEND);
//        List<String> lines= Files.readAllLines(dataPath);
//        for (String line: lines){
//            System.out.println(line);
//        }
//        }

        // Read write Binary Files
        try (FileOutputStream outputStream = new FileOutputStream("binData.dat");
             FileChannel channel = outputStream.getChannel()) {
            byte[] outputBytes = "Hello World!".getBytes();

            // Using .wrap() makes channel automate handle based on buffer and no flip required.
//            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            ByteBuffer buffer = ByteBuffer.allocate(outputBytes.length);
            buffer.put(outputBytes);
            buffer.flip();

            int numBytes = channel.write(buffer);
            System.out.println("Bytes written: " + numBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(256);
            // .flip resets the position to 0
            intBuffer.flip();
            numBytes = channel.write(intBuffer);
            System.out.println("Integer Bytes written: " + numBytes);

            // .flip is used to switch from read to write and vice versa.
            // data is read from buffer and written into channel, or vice versa, so flip method is used
            // For only reading/writing .flip is not required

            intBuffer.flip();
            intBuffer.putInt(-256);
            intBuffer.flip();
            numBytes = channel.write(intBuffer);
            System.out.println("Negative Integer Bytes written: " + numBytes);


            // Reading data from a file
            // Using java.io
//            RandomAccessFile randomAccessFile = new RandomAccessFile("binData.dat", "rwd");
//            byte[] inputBytes = new byte[outputBytes.length];
//            randomAccessFile.read(inputBytes);
//            System.out.println(new String(inputBytes));
//
//            long int1 = randomAccessFile.readInt();
//            long int2 = randomAccessFile.readInt();
//            System.out.println(int1 + "\n" + int2);

            // Using java.nio
            RandomAccessFile randomAccessFile = new RandomAccessFile("binData.dat", "rwd");
            FileChannel channel1 = randomAccessFile.getChannel();
            // This replaces the data inside memory and not the files being read
            outputBytes[0] = 'a';
            outputBytes[1] = 'b';
            System.out.println("outputBytes if flip skipped: " + new String(outputBytes));
            // If .flip() is skipped here, data will be read from file but printed from memory
            buffer.flip();
            long numBytesRead = channel1.read(buffer);
            System.out.println("outputBytes if flip included: " + new String(outputBytes));
            if (buffer.hasArray()) {
              System.out.println("byte buffer: " + new String(buffer.array()));
//                System.out.println("byte buffer: " + new String(outputBytes));
            }

            // Relative read, using flip method i.e. flip is required after channel read/write or buffer read operations.
//            intBuffer.flip();
//            numBytes=channel1.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            numBytes=channel1.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

            // Absoulte read, by passing index. It reduces flipping after getting the position.
            intBuffer.flip();
            numBytes = channel1.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytes = channel1.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

            channel1.close();
            randomAccessFile.close();

        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
