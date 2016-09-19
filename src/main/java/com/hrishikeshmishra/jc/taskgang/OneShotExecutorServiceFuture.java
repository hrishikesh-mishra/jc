package com.hrishikeshmishra.jc.taskgang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public class OneShotExecutorServiceFuture extends SearchTaskGangCommon {

    private List<Future<SearchResult>> resultFutures;

    public OneShotExecutorServiceFuture(String[] wordToFind, String[][] inputIterator) {
        super(wordToFind, inputIterator);
        setExecutor(Executors.newCachedThreadPool());
    }

    protected void processFutureResults(List<Future<SearchResult>> resultFutures){
        for (Future<SearchResult> resultFuture : resultFutures){
            try {
                resultFuture.get().print();
            } catch (Exception e) {
                System.out.printf("get() exception.");
            }
        }
    }

    @Override
    protected void initiateTaskGang(int inputSize) {
        resultFutures = new ArrayList<>(inputSize * wordToFind.length);
        for (final String inputData : getInput())
            processInput(inputData);

        processFutureResults(resultFutures);
    }

    @Override
    protected boolean processInput(final String inputData) {
        ExecutorService executorService = (ExecutorService) getExecutor();
        for (final String word:  wordToFind) {
            final Future<SearchResult> resultFuture = executorService.submit(() -> searchForWord(word, inputData));
            resultFutures.add(resultFuture);
        }
        return true;
    }
}


