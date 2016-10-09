package com.hrishikeshmishra.jc.bestmatching;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WordsLoader {

    public static List<String> load(String path) {
        try {
            return Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
