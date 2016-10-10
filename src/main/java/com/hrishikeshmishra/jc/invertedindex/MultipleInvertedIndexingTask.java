package com.hrishikeshmishra.jc.invertedindex;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by hrishikesh.mishra on 10/10/16.
 */
public class MultipleInvertedIndexingTask implements Runnable {

    private CompletionService<List<Document>> completionService;
    private ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex;

    public MultipleInvertedIndexingTask(CompletionService<List<Document>> completionService, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex) {
        this.completionService = completionService;
        this.invertedIndex = invertedIndex;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                try {
                    List<Document> documents = completionService.take().get();
                    for (Document document: documents){
                        updateInvertedIndex(document.getVoc(), invertedIndex, document.getFile());
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }

            while (true){
                Future<List<Document>> future = completionService.poll();
                if(future == null)
                    break;

                for (Document document : future.get()){
                    updateInvertedIndex(document.getVoc(), invertedIndex, document.getFile());
                }

            }
        } catch (InterruptedException| ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void updateInvertedIndex(Map<String, Integer> voc, ConcurrentHashMap<String, ConcurrentLinkedDeque<String>> invertedIndex, String fileName) {
        for (String word: voc.keySet()){
            if(word.length() >=3){
                invertedIndex.computeIfAbsent(word, k -> new ConcurrentLinkedDeque<String>()).add(fileName);
            }
        }
    }
}
