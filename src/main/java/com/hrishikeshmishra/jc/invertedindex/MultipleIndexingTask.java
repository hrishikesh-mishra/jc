package com.hrishikeshmishra.jc.invertedindex;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class MultipleIndexingTask implements Callable<List<Document>> {

    private List<File> files;

    public MultipleIndexingTask(List<File> files) {
        this.files = files;
    }

    @Override
    public List<Document> call() throws Exception {
        List<Document> documents = new ArrayList<>();
        for (File file: files){
            DocumentParser parser = new DocumentParser();
            Map<String, Integer> voc = parser.parse(file.getAbsolutePath());
            Document document = new Document();
            document.setFile(file.getName());
            document.setVoc(voc);
            documents.add(document);
        }

        return documents;
    }
}
