package com.hrishikeshmishra.jc.invertedindex;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class SerialIndexing {

    public static void main(String[] args) {
        Date start, end;
        File source = new File("data/indexing");
        File[] files = source.listFiles();
        Map<String, List<String>> invertedIndex = new HashMap<>();

        start = new Date();
        for (File file: files){
            DocumentParser parser = new DocumentParser();
            if(file.getName().endsWith(".txt")){
                Map<String, Integer> voc = parser.parse(file.getAbsolutePath());
                updateInvertedIndex (voc, invertedIndex, file.getName());
            }
        }
        end = new Date();


        System.out.println("Execution time: " + (end.getTime() - start.getTime()));
        System.out.println("InvertedIndex : " + invertedIndex.size());
        invertedIndex.keySet().forEach(System.out::println);

    }

    private static void updateInvertedIndex(Map<String, Integer> voc, Map<String, List<String>> invertedIndex, String fileName) {
        for (String word: voc.keySet()){
            if(word.length() >= 3){
                invertedIndex.computeIfAbsent(word, k-> new ArrayList<String>()).add(fileName);
            }
        }
    }
}
