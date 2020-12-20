package com.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class AccessDirectoryContent {
    public static void main(String[] args) {
        // separators i.e. / or \ depending on OS
        String seperator = File.separator;
        System.out.println("File.separator " + seperator);
        seperator = FileSystems.getDefault().getSeparator();
        System.out.println("FileSystems.getDefault().getSeparator() " + seperator);


//        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
//            @Override
//            public boolean accept(Path entry) throws IOException {
//                return Files.isRegularFile(entry);
//            }
//        };

        // Using lambda Expression
        // p is the variable of generic type
        DirectoryStream.Filter<Path> filter = p -> Files.isRegularFile(p);


//        Path dirPath = FileSystems.getDefault().getPath("createdDir/Dir1");

        Path dirPath = FileSystems.getDefault().getPath("createdDir" + File.separator + "Dir1");

        // all files and directories are listed
//        DirectoryStream<Path> pathDirectoryStream= Files.newDirectoryStream(dirPath)
        // specific files matching glob pattern are listed
//        DirectoryStream<Path> pathDirectoryStream= Files.newDirectoryStream(dirPath,"*.txt")
        // Filter the files files being listed using DirectoryStream.Filter<Path>
//        DirectoryStream<Path> pathDirectoryStream= Files.newDirectoryStream(dirPath,filter)
        try (DirectoryStream<Path> pathDirectoryStream = Files.newDirectoryStream(dirPath)) {
            // Returns files directly in the path
            for (Path file : pathDirectoryStream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException e) {
            e.printStackTrace();
        }


        // creating temporary file
        try {
            Path tempFile = Files.createTempFile("myapp", ".txt");
            System.out.println("Temporary File Path: " + tempFile.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Filestores are the partitions or volumes i.e. Windows, Data, etc.
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for (FileStore drive : stores) {
            System.out.println(drive);
            System.out.println(drive.name());
        }


        // Listing all the volumes in windows and root in linux/mac OS
        Iterable<Path> rootPaths = FileSystems.getDefault().getRootDirectories();
        for (Path path : rootPaths) {
            System.out.println(path);
        }


        System.out.println("******* Walking file Tree *******");
//        dirPath = FileSystems.getDefault().getPath("createdDir" + File.separator + "Dir1");
        try {
            Files.walkFileTree(dirPath, new PrintFileNames());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Copying createdDir/Dir1 to copyDir/Dir1Copy");
        Path copyPath = FileSystems.getDefault().getPath("copyFiles");
        try {
            Files.walkFileTree(dirPath, new copyFiles(dirPath, copyPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
