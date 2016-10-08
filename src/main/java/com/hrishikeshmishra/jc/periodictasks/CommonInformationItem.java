package com.hrishikeshmishra.jc.periodictasks;

public class CommonInformationItem {

    private String id;
    private String source;
    private String fileName;

    public CommonInformationItem(String id, String source, String fileName) {
        this.id = id;
        this.source = source;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "CommonInformationItem{" +
                "id='" + id + '\'' +
                ", source='" + source + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}



