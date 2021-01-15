package com.examples.networks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	try {
        InetAddress address=InetAddress.getLocalHost();
        DatagramSocket socket=new DatagramSocket();

        Scanner scanner=new Scanner(System.in);
        String echoString;
        do {
            System.out.println("Enter String to echo: ");
            echoString=scanner.nextLine();

            byte[] buffer=echoString.getBytes();

            DatagramPacket packet=new DatagramPacket(buffer, buffer.length,address,5000);
            socket.send(packet);

            // to get response back from server
            byte[] buffer2=new byte[50];
            packet=new DatagramPacket(buffer2, buffer2.length);
            socket.receive(packet);
            System.out.println("Text received: "+new String(buffer2));

        }while (!echoString.equals("exit"));

    }catch (SocketTimeoutException e){
        System.out.println("Socket Timed out");
    }catch (IOException e){
        System.out.println("Client Error: "+e.getMessage());
    }
    }
}
