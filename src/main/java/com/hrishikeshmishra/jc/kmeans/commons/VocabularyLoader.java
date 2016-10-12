package com.hrishikeshmishra.jc.kmeans.commons;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class VocabularyLoader  {

    public static Map<String, Integer> load(Path path){
        int index = 0;
        Map<String, Integer> vocIndex = new HashMap<>();
        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line ;
            while ((line = reader.readLine()) != null){
                vocIndex.put(line , index++);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vocIndex;
    }
}
