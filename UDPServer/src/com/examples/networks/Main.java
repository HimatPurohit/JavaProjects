package com.examples.networks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket(5000);

            while (true) {
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                System.out.println("Text Received : " + new String(buffer));


                // to respond back to the client
                String returnString="echo: "+new String(buffer);
                byte[] returnBuffer=returnString.getBytes();
                InetAddress address=packet.getAddress();
                int port=packet.getPort();
                packet=new DatagramPacket(returnBuffer, returnBuffer.length,address,port);
                socket.send(packet);

            }
        } catch (SocketException socketException) {
            System.out.println("SocketException: " + socketException.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
