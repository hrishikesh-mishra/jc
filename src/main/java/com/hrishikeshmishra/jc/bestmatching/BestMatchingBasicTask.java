package com.hrishikeshmishra.jc.bestmatching;

import com.hrishikeshmishra.jc.bestmatching.algo.LevenshteinDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class BestMatchingBasicTask implements Callable<BestMatchingData> {

    private int startIndex;
    private int endIndex;
    private List<String> dictionary;
    private String word;

    public BestMatchingBasicTask(int startIndex, int endIndex, List<String> dictionary, String word) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dictionary = dictionary;
        this.word = word;
    }

    @Override
    public BestMatchingData call() throws Exception {
        List<String> results = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        int distance;

        for (int i = startIndex; i < endIndex; i++) {
            String str = dictionary.get(i);
            distance = LevenshteinDistance.calculate(word, str);
            if (distance < minDistance) {
                results.clear();
                minDistance = distance;
                results.add(str);
            } else if (distance == minDistance) {
                results.add(str);
            }
        }

        BestMatchingData result = new BestMatchingData();
        result.setDistance(minDistance);
        result.setWords(results);
        return result;
    }
}
