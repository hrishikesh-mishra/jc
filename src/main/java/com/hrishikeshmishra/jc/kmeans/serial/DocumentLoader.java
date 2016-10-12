package com.hrishikeshmishra.jc.kmeans.serial;

import com.hrishikeshmishra.jc.kmeans.commons.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class DocumentLoader {

    public static Document [] load(Path path, Map<String, Integer> vocIndex) throws IOException {

        List<Document> documents = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(path)){
            String line;

            while ((line = reader.readLine()) != null){
                Document item = parseLine(line, vocIndex);
                documents.add(item);
            }
        }

        return documents.toArray( new Document[documents.size()]);

    }

    private static Document parseLine(String line, Map<String, Integer> vocIndex) {
        String [] tokens = line.split(",");
        int size = tokens.length -1;
        Document document = new Document(tokens[0], size);
        Word[] data = document.getWords();

        for (int i=1; i< tokens.length; i++){
            String[] wordInfo = tokens[i].split(":");
            Word word = new Word(vocIndex.get(wordInfo[0]), Double.parseDouble(wordInfo[1]));
            data[i-1] = word;
        }



        Arrays.sort(data);
        return document;
    }
}
