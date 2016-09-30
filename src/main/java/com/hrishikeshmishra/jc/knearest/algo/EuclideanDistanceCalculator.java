package com.hrishikeshmishra.jc.knearest.algo;


import com.hrishikeshmishra.jc.knearest.models.Sample;

public class EuclideanDistanceCalculator {

    public static double calculate(Sample example1, Sample example2){
        double ret = 0.0d;
        double [] data1 = example1.getExamples();
        double [] data2 = example2.getExamples();

        if(data1.length != data2.length) {
            throw new IllegalArgumentException("Vector doesn't have same length ");
        }

        for (int i =0; i < data1.length ; i++){
            ret += Math.pow(data1[i] - data2[i], 2);
        }

        return Math.sqrt(ret);
    }
}
