package com.hrishikeshmishra.jc.keywordextraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class DocumentParser {

    public static Document parse(String path){
        Document document = new Document();
        Path file = Paths.get(path);
        document.setFileName(file.toString());

        try {
            for(String line: Files.readAllLines(file)){
                parseLine(line, document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    private static void parseLine(String line, Document document) {
        line = Normalizer.normalize(line, Normalizer.Form.NFKD);
        line = line.replaceAll("[^\\p{ASCII}]", "");
        line = line.toLowerCase();

        for(String w: line.split("\\W+")){
            if(w.length() >= 3)
            document.addWord(w);
        }
    }
}
