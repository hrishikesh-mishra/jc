package com.hrishikeshmishra.jc.knearest.models;

public class Distance {

    private int index;
    private double distance;

    public Distance(int index, double distance) {
        this.index = index;
        this.distance = distance;
    }

    public Distance() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "index=" + index +
                ", distance=" + distance +
                '}';
    }
}

