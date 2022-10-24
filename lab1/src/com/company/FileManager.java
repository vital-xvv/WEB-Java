package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager implements Callable<String> {

    private final String fileName;
    private File file;
    private final Pattern pat1 = Pattern.compile("/\\*[\\w\\s\\p{Punct}&&[^*/]]*\\*/");
    private final Pattern pat2 = Pattern.compile("//.*");

    public FileManager(String fileName) {
        file = new File(fileName);
        if(file.exists()) this.fileName = fileName;
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String call() throws Exception {

        String fileContents = new String(Files.readAllBytes(Paths.get(fileName)));


        File fileCopy = new File(fileName.replaceAll("\\.java", "") + "-copy.java");
        if(!fileCopy.exists()) fileCopy.createNewFile();

        Matcher mat1 = pat1.matcher(fileContents);
        Matcher mat2 = pat2.matcher(mat1.replaceAll(""));
        String editedText = mat2.replaceAll("");

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileCopy));
        writer.write(editedText);
        writer.flush();
        writer.close();


        //Thread.sleep(3000);

        return "In " + Thread.currentThread().getName() + " created a file - " + fileCopy.getAbsolutePath();
    }


}
