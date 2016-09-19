package com.hrishikeshmishra.jc.taskgang;

import java.util.concurrent.*;

/**
 * Created by hrishikesh.mishra on 20/09/16.
 */
public class OneShotExecutorCompletionService extends SearchTaskGangCommon {

    private ExecutorCompletionService<SearchResult> completionService;

    public OneShotExecutorCompletionService(String[] wordToFind, String[][] inputIterator) {
        super(wordToFind, inputIterator);
        setExecutor(Executors.newCachedThreadPool());
        completionService = new ExecutorCompletionService<SearchResult>(getExecutor());
    }

    protected void concurrentlyProcessQueuedFutures(){
        final int count = getInput().size() * wordToFind.length;
        for (int i = 0; i< count; i++){
            final Future<SearchResult> resultFuture;
            try {
                resultFuture = completionService.take();
                resultFuture.get().print();
            } catch (Exception e) {
                System.out.println("concurrentlyProcessQueuedFutures() exception.");
            }
        }
    }

    @Override
    protected void initiateTaskGang(int inputSize) {
        for (int i=0; i< inputSize; i++)
            getExecutor().execute(makeTask(i));

        concurrentlyProcessQueuedFutures();
    }

    @Override
    protected boolean processInput(String inputData) {
        for (final String word: wordToFind)
            completionService.submit(() -> searchForWord(word, inputData));
        return true;
    }
}
