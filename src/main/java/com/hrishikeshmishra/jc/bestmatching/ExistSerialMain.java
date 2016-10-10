package com.hrishikeshmishra.jc.bestmatching;

import java.util.Date;
import java.util.List;

public class ExistSerialMain {

    public static void main(String[] args) {
        Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("data/UK-Advanced-Cryptics-Dictionary.txt");
        System.out.println("Dictionary Size: " + dictionary.size());

        if(args.length == 0){
            args = new String[1];
            args[0] = "cat";
        }

        startTime = new Date();
        boolean result = ExistSerialCalculation.existsWord(args[0], dictionary);
        endTime = new Date();
        System.out.println("Word: " + args[0]);
        System.out.println("Exists : " + result);
        System.out.println("Execution Time: " + (endTime.getTime() - startTime.getTime()));

    }

}

