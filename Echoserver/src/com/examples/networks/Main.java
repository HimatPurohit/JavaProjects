package com.examples.networks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        // ServerSocket serverSocket=new ServerSocket(port number)
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            // single client
//            Socket socket = serverSocket.accept();
//            System.out.println("Client Connected");
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                // Multiple clients
                new Echoer(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
//	    e.printStackTrace();
        }
    }
}
