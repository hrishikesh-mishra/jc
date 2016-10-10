package com.hrishikeshmishra.jc.invertedindex;

import java.util.Map;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class Document {
    private String file;
    private Map<String, Integer> voc;

    public Document() {
    }

    public Document(String file, Map<String, Integer> voc) {
        this.file = file;
        this.voc = voc;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Map<String, Integer> getVoc() {
        return voc;
    }

    public void setVoc(Map<String, Integer> voc) {
        this.voc = voc;
    }
}
