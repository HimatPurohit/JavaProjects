package com.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main {

    public static void main(String[] args) {

        // Reading/Accessing Files from different locations

        Path workingDirectory = FileSystems.getDefault().getPath("workingDirectory.txt");
        Path subDirectory1 = FileSystems.getDefault().getPath("files/subDirectory.txt");
        Path outsideDirectory = FileSystems.getDefault().getPath(".", "..", "outsideDirectory.txt");
        Path anotherDirectory = FileSystems.getDefault().getPath("..", "path reference", "anotherDirectory.txt");

        // pass directories as parameter
        Path subDirectory2 = FileSystems.getDefault().getPath("files", "subDirectory.txt");
        // passes current directory "." followed by all directories
        Path subDirectory3 = FileSystems.getDefault().getPath(".", "files", "subDirectory.txt");


        // using / to access directories
//        Path outsideDirectory = Paths.get("../outsideDirectory.txt");
//        Path anotherDirectory = Paths.get("../path reference/anotherDirectory.txt");
        // using \\ as per windows recommendation but not compulsory
//        Path outsideDirectory = Paths.get("..\\outsideDirectory.txt");
//        Path anotherDirectory = Paths.get("..\\path reference\\anotherDirectory.txt");
//        Path outsideDirectory = Paths.get(".", "..", "outsideDirectory.txt");
//        Path anotherDirectory = Paths.get(".", "..", "path reference", "anotherDirectory.txt");

        Path dummyPath = FileSystems.getDefault().getPath(".", "filedir", "subDirectory.txt");

        System.out.println("Path Exists:");
        System.out.println("workingDirectory " + Files.exists(workingDirectory)
                + "\nsubDirectory1 " + Files.exists(subDirectory1)
                + "\nanotherDirectory " + Files.exists(anotherDirectory)
                + "\noutsideDirectory " + Files.exists(outsideDirectory));
        System.out.println("dummyPath " + Files.exists(dummyPath));

        System.out.println("\n isReadable isWritable isExecutable");
        System.out.println("workingDirectory is Readable: " + Files.isReadable(workingDirectory));
        System.out.println("workingDirectory is Writable: " + Files.isWritable(workingDirectory));
        System.out.println("workingDirectory is Executable: " + Files.isExecutable(workingDirectory));
        System.out.println("dummyPath is Readable: " + Files.isWritable(dummyPath));

        System.out.println("");
        System.out.println("workingDirectory: " + workingDirectory.toAbsolutePath());
        printFile(workingDirectory);
        System.out.println("subDirectory1: " + subDirectory1.toAbsolutePath());
        printFile(subDirectory1);
        System.out.println("subDirectory2: " + subDirectory2.toAbsolutePath());
        printFile(subDirectory2);
        System.out.println("subDirectory3: " + subDirectory3.toAbsolutePath());
        printFile(subDirectory3);
        System.out.println("outsideDirectory: " + outsideDirectory.toAbsolutePath());
        printFile(outsideDirectory);
        System.out.println("anotherDirectory: " + anotherDirectory.toAbsolutePath());
        printFile(anotherDirectory);


    }

    private static void printFile(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
