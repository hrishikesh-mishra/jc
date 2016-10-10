package com.hrishikeshmishra.jc.bestmatching;


import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExistBasicConcurrentMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("data/UK-Advanced-Cryptics-Dictionary.txt");
        System.out.println("Dictionary Size: " + dictionary.size());

        if(args.length == 0){
            args = new String[1];
            args[0] = "hrishikesh";
        }

        startTime = new Date();
        boolean result = ExistBasicConcurrentCalculation.existWord(args[0], dictionary);
        endTime = new Date();
        System.out.println("Word: " + args[0]);
        System.out.println("Exists : " + result);
        System.out.println("Execution Time: " + (endTime.getTime() - startTime.getTime()));
    }
}
