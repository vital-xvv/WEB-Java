package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileTreeWalker {

    private final ExecutorService executor = Executors.newCachedThreadPool();
    public final String dirName;

    public FileTreeWalker(String dirName) {
        this.dirName = dirName;
    }




    private void walkDirectory(String dirName) {

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
                System.err.println("'" + file.getName() + "'" + " is a file not a directory");
                throw new IllegalArgumentException();
            }
        }
        else {
            System.err.println("'" + file.getName() + "'" + " directory doesn't exist");
            throw new IllegalArgumentException();
        }

    }

    public void walkDirectory() {

        File file = new File(this.dirName);
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

                executor.shutdown();

            }
            else {
                System.err.println("'" + file.getName() + "'" + " is a file not a directory");
                throw new IllegalArgumentException();
            }
        }
        else {
            System.err.println("'" + file.getName() + "'" + " directory doesn't exist");
            throw new IllegalArgumentException();
        }

    }

    private void manageFile(String fileName) throws IOException, ExecutionException, InterruptedException {

        File file = new File(fileName);
        if(file.exists()) {
            if(file.getName().endsWith(".java") && !file.getName().contains("-copy")){
                System.out.println(executor.submit(new FileManager(fileName)).get());

            }
        }
        else {
            System.err.println("'" + file.getName() + "'" + " file doesn't exist");
            throw new IllegalArgumentException();
        }

    }
}
