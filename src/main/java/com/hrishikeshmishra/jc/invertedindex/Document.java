package com.hrishikeshmishra.jc.invertedindex;

import java.util.List;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class Document {
    private String file;
    private List<String> words;

    public Document(String file, List<String> words) {
        this.file = file;
        this.words = words;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        return "Document{" +
                "file='" + file + '\'' +
                ", words=" + words +
                '}';
    }
}
