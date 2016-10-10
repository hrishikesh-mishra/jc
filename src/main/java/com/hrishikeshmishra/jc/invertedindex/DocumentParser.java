package com.hrishikeshmishra.jc.invertedindex;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class DocumentParser {

    private static final Pattern PATTERN = Pattern.compile("\\P{IsAlphabetic}+");
    public Map<String, Integer> parse (String source){
        Map<String, Integer> ret = new HashMap<>();
        Path file = Paths.get(source);
        try(BufferedReader reader  = Files.newBufferedReader(file)){
            String line  = null;

            while ((line = reader.readLine()) != null){
                pareseLine(line, ret);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void pareseLine(String line, Map<String, Integer> ret) {

        for (String word : PATTERN.split(line)){
            if(!word.isEmpty()){
                ret.merge(Normalizer.normalize(word, Normalizer.Form.NFKD).toLowerCase(), 1, (a, b) -> a+b);
            }
        }

    }
}
