package com.hrishikeshmishra.jc.knearest;


import com.hrishikeshmishra.jc.knearest.algo.EuclideanDistanceCalculator;
import com.hrishikeshmishra.jc.knearest.models.Distance;
import com.hrishikeshmishra.jc.knearest.models.Sample;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GroupDistanceTask implements Runnable {

    private final Distance[] distances;
    private final int startIndex;
    private final int endIndex;
    private final List<? extends Sample> dataSet;
    private final Sample example;
    private final CountDownLatch endController;

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, List<? extends Sample> dataSet, Sample example, CountDownLatch endController) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataSet = dataSet;
        this.example = example;
        this.endController = endController;
    }

    @Override
    public void run() {

        for (int index = startIndex;  startIndex < endIndex ; index++){
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(EuclideanDistanceCalculator.calculate(dataSet.get(index), example));
            index++;
        }

        endController.countDown();
    }
}
