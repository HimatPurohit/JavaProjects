package com.examples;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class copyFiles extends SimpleFileVisitor<Path> {
    private Path sourceRoot;
    private Path targetRoot;

    public copyFiles(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativizedPath = sourceRoot.relativize(file);
        System.out.println("relativizedPath: " + relativizedPath);

        Path copyPath = targetRoot.resolve(relativizedPath);
        System.out.println("resolved path for copy: " + copyPath);
        try {
//            Files.copy(file,copyPath);
            Files.copy(file,copyPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path relativizedPath = sourceRoot.relativize(dir);
        System.out.println("relativizedPath dir: " + relativizedPath + " dir path: "+dir.toAbsolutePath());

        Path copyPath = targetRoot.resolve(relativizedPath);
        System.out.println("resolved path for copy: " + copyPath);
        try {
//            Files.copy(dir,copyPath);
            Files.copy(dir,copyPath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing File " + file.toAbsolutePath() + " " + exc.getMessage());
        return FileVisitResult.CONTINUE;
    }
}
