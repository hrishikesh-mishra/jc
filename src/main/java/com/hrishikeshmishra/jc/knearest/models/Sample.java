package com.hrishikeshmishra.jc.knearest.models;

import java.util.Arrays;


public class Sample {

    private double [] examples;
    private String tag;

    public Sample(double[] examples, String tag) {
        this.examples = examples;
        this.tag = tag;
    }

    public double[] getExamples() {
        return examples;
    }

    public void setExamples(double[] examples) {
        this.examples = examples;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "examples=" + Arrays.toString(examples) +
                ", tag='" + tag + '\'' +
                '}';
    }
}
