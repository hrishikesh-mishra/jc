package com.hrishikeshmishra.jc.keywordextraction;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class Keyword implements Comparable<Keyword> {

    private String word;
    private int df;

    public Keyword(String word, int df) {
        this.word = word;
        this.df = df;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    @Override
    public int compareTo(Keyword o) {
        return Integer.compare(o.df, this.df);
    }
}
