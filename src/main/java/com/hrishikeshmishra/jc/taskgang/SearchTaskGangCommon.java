package com.hrishikeshmishra.jc.taskgang;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public abstract class SearchTaskGangCommon extends TaskGang<String> {

    protected final String[] wordToFind;

    protected final Iterator<String[]> inputIterator;

    public SearchTaskGangCommon(String[] wordToFind, String[][] inputIterator) {
        this.wordToFind = wordToFind;
        this.inputIterator = Arrays.asList(inputIterator).iterator();
    }

    @Override
    protected List<String> getNextInput() {
        if(inputIterator.hasNext()){
            incrementCycle();
            return Arrays.asList(inputIterator.next());
        }
        return null;
    }

    protected SearchResult searchForWord(String word, String inputData){
        SearchResult results = new SearchResult(Thread.currentThread().getId(),
                                                    currentCycle(),
                                                    word,
                                                    inputData );
        for (int i= inputData.indexOf(word, 0);
                i != -1;
                i = inputData.indexOf(word, i + word.length()) ) {
            results.add(i);
        }
        return results;
    }

    protected void awaitTasksDone(){
        if(getExecutor() instanceof ExecutorService){
            ExecutorService executorService = (ExecutorService) getExecutor();
            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {

            }
        }
    }
}
