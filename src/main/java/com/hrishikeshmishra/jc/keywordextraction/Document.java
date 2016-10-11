package com.hrishikeshmishra.jc.keywordextraction;

import java.util.HashMap;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class Document {
    private String fileName;
    private HashMap<String, Word> voc;

    public Document(String fileName) {
        this();
        this.fileName = fileName;
    }

    public Document() {
        this.voc = new HashMap<>();
    }

    public void addWord(String string){
        voc.computeIfAbsent(string, k-> new Word(k) ).addTf();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public HashMap<String, Word> getVoc() {
        return voc;
    }
}
