package com.hrishikeshmishra.jc.knearest;


import com.hrishikeshmishra.jc.knearest.algo.EuclideanDistanceCalculator;
import com.hrishikeshmishra.jc.knearest.models.Distance;
import com.hrishikeshmishra.jc.knearest.models.Sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KnnClassifier {

    private List<? extends Sample> dataSet;
    private int k;

    public KnnClassifier(List<? extends Sample> dataSet, int k) {
        this.dataSet = dataSet;
        this.k = k;
    }

    public String classify(Sample example){
        Distance [] distances = new Distance[dataSet.size()];
        int index = 0;

        for (Sample localExample : dataSet){
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(localExample, example));
            index++;
        }

        Arrays.sort(distances);

        Map<String, Integer> results = new HashMap<>();
        for (int i =0 ; i < k; i++){
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) -> a+b );
        }

        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
