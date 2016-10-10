package com.hrishikeshmishra.jc.bestmatching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class BestMatchingAdvancedConcurrentCalculation {

    public static BestMatchingData getBestMatchingData(String word, List<String> dictionary) throws ExecutionException, InterruptedException {
        int numCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);

        int size = dictionary.size();
        int step = size / numCores;
        int startIndex, endIndex;
        List<BestMatchingBasicTask> tasks = new ArrayList<>();
        List<Future<BestMatchingData>> results = new ArrayList<>();

        for (int i = 0; i < numCores; i++){
            startIndex = i * step;
            if(i == numCores -1){
                endIndex = dictionary.size();
            }else {
                endIndex = (i+1) * step;
            }
            BestMatchingBasicTask task = new BestMatchingBasicTask(startIndex, endIndex, dictionary, word);
            tasks.add(task);
        }

        List<Future<BestMatchingData>> futures = executor.invokeAll(tasks);
        executor.shutdown();


        List<String> words = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (Future<BestMatchingData> future: futures){
            BestMatchingData data = future.get();
            if(data.getDistance() < minDistance){
                words.clear();
                minDistance = data.getDistance();
                words.addAll(data.getWords());
            } else if(data.getDistance() == minDistance) {
                words.addAll(data.getWords());
            }
        }

        BestMatchingData result = new BestMatchingData();
        result.setDistance(minDistance);
        result.setWords(words);
        return result;
    }

}
