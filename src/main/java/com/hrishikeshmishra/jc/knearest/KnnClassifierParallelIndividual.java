package com.hrishikeshmishra.jc.knearest;

import com.hrishikeshmishra.jc.knearest.models.Distance;
import com.hrishikeshmishra.jc.knearest.models.Sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class KnnClassifierParallelIndividual {

    private List<? extends Sample> dataSet;
    private int k;
    private ThreadPoolExecutor executor;
    private int numThreads;
    private boolean parallelSort;

    public KnnClassifierParallelIndividual(List<? extends Sample> dataSet, int k, int factor, boolean parallelSort) {
        this.dataSet = dataSet;
        this.k = k;
        this.numThreads = factor * (Runtime.getRuntime().availableProcessors());
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(this.numThreads);
        this.parallelSort = parallelSort;
    }

    public String classify(Sample example) throws InterruptedException {
        Distance[] distances = new Distance[dataSet.size()];
        CountDownLatch endController  = new CountDownLatch(dataSet.size());


        int index = 0;

        for (Sample localExample : dataSet){
            IndividualDistanceTask task = new IndividualDistanceTask(distances,
                    index, localExample, example, endController);

            executor.execute(task);
            index++;

        }

        endController.await();

        if(parallelSort) {
            //Arrays.parallelSort(distances);
            Arrays.sort(distances);
        } else {
            Arrays.sort(distances);
        }


        Map<String, Integer> results = new HashMap<>();
        for (int i =0 ; i < k; i++){
            Sample localExample = dataSet.get(distances[i].getIndex());
            String tag = localExample.getTag();
            results.merge(tag, 1, (a, b) -> a+b );
        }

        return Collections.max(results.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void destroy() {
        executor.shutdown();
    }

}
