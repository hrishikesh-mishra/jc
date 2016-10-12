package com.hrishikeshmishra.jc.geneticalgo.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class DataLoader {

    public static int [][] load(String fileName){
        Path path = Paths.get(fileName);

        try {
            List<String> lines = Files.readAllLines(path);
            if(lines == null || lines.size() == 0)
                throw new NoSuchElementException("City data not found.");

            List<Integer> firstRow = parseColumn(lines.get(0));

            if(firstRow.size() == 0)
                throw new NoSuchElementException("City data not found.");

            int [][] citiesDistance = new int[firstRow.size()][firstRow.size()];

            int counter = 0;
            for (String line : lines){
                citiesDistance[counter++] =  parseColumn(line).stream().mapToInt(i->i).toArray();
            }

            return citiesDistance;

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new NoSuchElementException("City data not found.");
    }


    private static List<Integer> parseColumn(String line){
        List<Integer> columns = new ArrayList<>();
        for(String w: line.split("\\W+")){
            if(!w.isEmpty()){
                columns.add(Integer.parseInt(w));
            }
        }

        return columns;
    }
}
