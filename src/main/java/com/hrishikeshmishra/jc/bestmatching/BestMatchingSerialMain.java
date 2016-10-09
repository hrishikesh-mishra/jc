package com.hrishikeshmishra.jc.bestmatching;


import java.util.Date;
import java.util.List;

public class BestMatchingSerialMain {

    public static void main(String[] args) {
        Date startTime, endTime;
        List<String> dictionary = WordsLoader.load("data/UK-Advanced-Cryptics-Dictionary.txt");
        System.out.println("Dictionary Size: " + dictionary.size());

        if(args.length == 0){
            args = new String[1];
            args[0] = "hrishikesh";
        }

        startTime = new Date();
        BestMatchingData result = BestMatchingSerialCalculation.getBestMatchingWords(args[0], dictionary);
        List<String> results = result.getWords();
        endTime = new Date();
        System.out.println("Word :" + args[0]);
        System.out.println("Minimum distance : " + result.getDistance());
        System.out.println("List of best matching words : " + results.size());
        results.forEach(System.out::println);
        System.out.println("Execution Time: " + (endTime.getTime() - startTime.getTime()));

    }
}
