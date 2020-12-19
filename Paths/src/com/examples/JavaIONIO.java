package com.examples;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaIONIO {
    public static void main(String[] args) {
        File file = new File("files/subDirectory.txt");
        // Java IO to Java NIO
        Path convertedPath = file.toPath();
        System.out.println("convertedPath " + convertedPath);


        File parent = new File("createdDir");
        File resolvedFile = new File(parent, "Dir1/subDirectory.txt");
        System.out.println("resolvedFile.toPath() " + resolvedFile.toPath());
        resolvedFile = new File("createdDir", "Dir1/subDirectory.txt");
        System.out.println("resolvedFile.toPath() " + resolvedFile.toPath());


        // Resolving the path using JAVA NIO
        Path parentPath = Paths.get("createdDir");
        Path childRelativePath = Paths.get("Dir1/subDirectory.txt");
        System.out.println(parentPath.resolve(childRelativePath));


        File workingDirectory = new File("").getAbsoluteFile();
        System.out.println("workingDirectory " + workingDirectory.getAbsolutePath());


        File copyFiles = new File(workingDirectory, "copyFiles/Dir1Copy");

        // Print copyFiles contents using list
        System.out.println("List files within " + copyFiles + " using list");
        String[] copyFilesContent = copyFiles.list();
        for (int i = 0; i < copyFilesContent.length; i++) {
            System.out.println("i: " + i + " " + copyFilesContent[i]);
        }


        // Print copyFiles contents using listFile
        System.out.println("List files within " + copyFiles + " using listFile");
        File[] copyFileList = copyFiles.listFiles();
        for (int i = 0; i < copyFileList.length; i++) {
            System.out.println("i: " + i + " " + copyFileList[i].getName());
        }
    }
}
