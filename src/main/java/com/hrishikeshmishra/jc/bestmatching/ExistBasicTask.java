package com.hrishikeshmishra.jc.bestmatching;


import com.hrishikeshmishra.jc.bestmatching.algo.LevenshteinDistance;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;


public class ExistBasicTask implements Callable<Boolean> {

    private int startIndex;
    private int endInded;
    private List<String> dictionary;
    private String word;

    public ExistBasicTask(int startIndex, int endInded, List<String> dictionary, String word) {
        this.startIndex = startIndex;
        this.endInded = endInded;
        this.dictionary = dictionary;
        this.word = word;
    }

    @Override
    public Boolean call() throws Exception {
        for(int i=startIndex; i < endInded; i++){
            if(LevenshteinDistance.calculate(word, dictionary.get(i)) == 0)
                return true;
        }

        if(Thread.interrupted())
            return false;

        throw new NoSuchElementException("The word "+ word + " doesn't exists.");
    }
}
