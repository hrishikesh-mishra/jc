package com.hrishikeshmishra.jc.bestmatching;


import java.util.List;

public class BestMatchingData {
    private List<String> words;
    private int distance;

    public void setWords(List<String> words) {
        this.words = words;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public List<String> getWords() {
        return words;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "BestMatchingData{" +
                "words=" + words +
                ", distance=" + distance +
                '}';
    }
}
