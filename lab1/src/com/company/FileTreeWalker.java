package com.company;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileTreeWalker {

    private final ExecutorService executor = Executors.newCachedThreadPool();


    public void walkDirectory(String dirName) {

        File file = new File(dirName);
        if(file.exists()) {
            if(file.isDirectory()){
                try {
                    for (File f : Objects.requireNonNull(file.listFiles())) {
                        if (f.isDirectory()) {
                            walkDirectory(f.getAbsolutePath());
                        } else if (f.isFile()) {
                            manageFile(f.getAbsolutePath());
                        }
                    }
                }catch (Exception e) {
                    return;
                }
            }
            else {
                System.err.println("'" + file.getName() + "'" + " directory doesn't exist");
                throw new IllegalArgumentException();
            }
        }
        else {
            System.err.println("'" + file.getName() + "'" + " directory doesn't exist");
            throw new IllegalArgumentException();
        }

    }

    public void manageFile(String fileName) {

        File file = new File(fileName);
        if(file.exists()) {
            if(file.getName().endsWith(".java")){
                System.out.println(file.getAbsolutePath());
            }
        }
        else {
            System.err.println("'" + file.getName() + "'" + " file doesn't exist");
            throw new IllegalArgumentException();
        }

    }
}
