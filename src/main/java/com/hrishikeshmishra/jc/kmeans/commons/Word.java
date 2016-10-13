package com.hrishikeshmishra.jc.kmeans.commons;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class Word implements Comparable<Word> {
    private int index;
    private double tfIdf;

    public Word() {
    }

    public Word(int index, double tfIdf) {
        this.index = index;
        this.tfIdf = tfIdf;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getTfIdf() {
        return tfIdf;
    }

    public void setTfIdf(double tfIdf) {
        this.tfIdf = tfIdf;
    }

    @Override
    public String toString() {
        return "Word{" +
                "index=" + index +
                ", tfIdf=" + tfIdf +
                '}';
    }

    @Override
    public int compareTo(Word o) {
        return Integer.compare(this.getIndex(), o.getIndex());
    }
}
