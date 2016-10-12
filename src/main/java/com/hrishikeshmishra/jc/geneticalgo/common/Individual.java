package com.hrishikeshmishra.jc.geneticalgo.common;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class Individual implements Comparable<Individual> {

    private Integer [] chromosomes;
    private int value;

    public Individual(int size) {
       chromosomes = new Integer[size];
    }

    public Individual(Individual other) {
        chromosomes = other.getChromosomes().clone();
    }

    public Integer[] getChromosomes() {
        return chromosomes;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Individual o) {
        return Integer.compare(this.getValue(), o.getValue());
    }
}
