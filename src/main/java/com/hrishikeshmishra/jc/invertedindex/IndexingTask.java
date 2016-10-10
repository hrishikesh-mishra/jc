package com.hrishikeshmishra.jc.invertedindex;

import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class IndexingTask implements Callable<Document> {

    private File file;
    public IndexingTask(File file) {
        this.file = file;
    }

    @Override
    public Document call() throws Exception {
        DocumentParser parser = new DocumentParser();
        Map<String, Integer> voc = parser.parse(file.getAbsolutePath());
        Document document = new Document();
        document.setVoc(voc);
        document.setFile(file.getName());
        return document;
    }
}
