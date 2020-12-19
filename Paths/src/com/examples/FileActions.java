package com.examples;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileActions {
    // Copy Move Rename Delete Files and Directories
    public static void main(String[] args) {
        try {

            Path sourceFile = FileSystems.getDefault().getPath("files/subDirectory.txt");
            Path copyPath = FileSystems.getDefault().getPath("subDirectoryCopy.txt");

            // Copying File

//            if (!Files.exists(copyPath)) {
//                // Copies file if it doesn't exists at the destination path i.e. copyPath
//                Files.copy(sourceFile, copyPath);
//            } else {
//                System.out.println("File Exists");
//            }
            // Replaces the destination file with new File
            Files.copy(sourceFile, copyPath, StandardCopyOption.REPLACE_EXISTING);

            Path sourceDir = FileSystems.getDefault().getPath("files");
            Path copyDir = FileSystems.getDefault().getPath("copyFiles");

            // Copying directory

//            if (!Files.exists(copyPath)) {
//                // Copies file directory and not files within if it doesn't exists at the destination path i.e. copyPath
//                Files.copy(sourceDir, copyDir);
//            } else {
//                System.out.println("File Directory Exists");
//            }
            // Replaces the destination dir with new File
            Files.copy(sourceDir, copyDir, StandardCopyOption.REPLACE_EXISTING);

            // Moving Files
            Path filesToMove = FileSystems.getDefault().getPath("subDirectoryCopy.txt");
            Path destination = FileSystems.getDefault().getPath("files", "subDirectoryCopy.txt");
            Files.move(filesToMove, destination,StandardCopyOption.REPLACE_EXISTING);

            // Renaming Files and directory (similar to move)
            filesToMove = FileSystems.getDefault().getPath("files", "subDirectoryCopy.txt");
            destination = FileSystems.getDefault().getPath("files", "subDirectoryCopy2.txt");
            Files.move(filesToMove, destination,StandardCopyOption.REPLACE_EXISTING);

            // Deleting Files and directories
//            Files.deleteIfExists(copyPath);
//            Files.delete(copyDir);
//            Files.delete(destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
