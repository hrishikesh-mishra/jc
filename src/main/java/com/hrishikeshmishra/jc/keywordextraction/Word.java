package com.hrishikeshmishra.jc.keywordextraction;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class Word implements Comparable<Word>{

    private String word;
    private int tf;
    private int df;
    private double tdIdf;

    public Word(String word) {
        this.word = word;
        this.df = 1;
    }

    public void setDf(int df , int N){
        this.df = df;
        this.tdIdf = tf * Math.log(Double.valueOf(N) / df);
    }

    @Override
    public int compareTo(Word o) {
        return Double.compare(o.tdIdf, this.tdIdf);
    }

    public void addTf() {
        tf++;
    }

    public  Word  merge(Word w1){
        //Word temp = new Word(w1.getWord());
        this.df = this.getDf()+ w1.df;
        this.tf = this.getTf() + w1.getTf();
        return this;
    }

    public String getWord() {
        return word;
    }

    public int getTf() {
        return tf;
    }

    public int getDf() {
        return df;
    }

    public double getTdIdf() {
        return tdIdf;
    }
}
