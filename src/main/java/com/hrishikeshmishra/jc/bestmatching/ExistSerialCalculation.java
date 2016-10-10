package com.hrishikeshmishra.jc.bestmatching;


import com.hrishikeshmishra.jc.bestmatching.algo.LevenshteinDistance;

import java.util.List;

public class ExistSerialCalculation {

    public static boolean existsWord(String word, List<String> dictionary){
        for (String str: dictionary){
            if(LevenshteinDistance.calculate(word, str) == 0)
                return true;
        }
        return false;
    }
}
