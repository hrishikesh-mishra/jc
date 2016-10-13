package com.hrishikeshmishra.jc.kmeans.commons;

import java.util.Arrays;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class Document {
    private Word[] words;
    private String fileName;
    private DocumentCluster cluster;

    public Document() {
    }

    public Document( String fileName, int size) {
        this.words = new Word[size];
        this.fileName = fileName;
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DocumentCluster getCluster() {
        return cluster;
    }

    public boolean setCluster(DocumentCluster cluster) {
        if(this.cluster == cluster){
            return false;
        }else {
            this.cluster = cluster;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Document{" +
                "words=" + Arrays.toString(words) +
                ", fileName='" + fileName + '\'' +
                ", cluster=" + cluster +
                '}';
    }
}
