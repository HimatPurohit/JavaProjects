package com.examples;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileAttributes {
    public static void main(String[] args) {
        try {
//            Path fileToCreate = FileSystems.getDefault().getPath("files/createdSubDirectory.txt");
//            Files.createFile(fileToCreate);
//            Path dirToCreate=FileSystems.getDefault().getPath("createdDir");
//            Files.createDirectory(dirToCreate);
//            Path dirsToCreate=FileSystems.getDefault().getPath("createdDir/Dir1/Dir2/Dir3");
//            Files.createDirectories(dirsToCreate);

            // File Attributes give details about the file like size
            Path filePath = FileSystems.getDefault().getPath("workingDirectory.txt");
            long size = Files.size(filePath);
            System.out.println("workingDirectory.txt size: " + size);
            System.out.println("workingDirectory.txt last modified: " + Files.getLastModifiedTime(filePath));

            // Retrieve all basic/common file attributes at once
            BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("size: " + attributes.size());
            System.out.println("last modifies: " + attributes.lastModifiedTime());
            System.out.println("last accessed: " + attributes.lastAccessTime());
            System.out.println("Is Directory: " + attributes.isDirectory());
            System.out.println("is Regular File: " + attributes.isRegularFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
