package com.hrishikeshmishra.jc.taskgang;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public class OneShotExecutorService extends SearchTaskGangCommon {

    protected CountDownLatch exitBarrier = null;
    private BlockingQueue<SearchResult> resultQueue = new LinkedBlockingDeque<>();
    protected final int MAX_THREAD = 4;

    public OneShotExecutorService(String[] wordToFind, String[][] inputIterator) {
        super(wordToFind, inputIterator);
    }

    @Override
    protected void initiateHook(int inputSize) {
        System.out.println("@@@ starting cycle "
                            + currentCycle()
                            + " with "
                            + inputSize
                            + " tasks@@@"
                            );
        exitBarrier = new CountDownLatch(inputSize);
        if(getExecutor() == null)
            setExecutor(Executors.newFixedThreadPool(MAX_THREAD));
    }

    @Override
    protected void initiateTaskGang(int inputSize) {
        initiateHook(inputSize);
        List<Callable<Object>> workerCollection = new ArrayList<>(inputSize);

        for (int i =0; i < inputSize; ++i)
            workerCollection.add(Executors.callable(makeTask(i)));

        ExecutorService executorService = (ExecutorService) getExecutor();
        try {
            executorService.invokeAll(workerCollection);
        } catch (InterruptedException e) {
            System.out.println("invokeAll() interrupted!");
        }
    }

    @Override
    protected boolean processInput(String inputData) {
        for (String word: wordToFind){
            queueResults(searchForWord(word, inputData));
        }

        return true;
    }

    @Override
    protected void taskDone(int index) throws IndexOutOfBoundsException {
        exitBarrier.countDown();
    }

    protected void queueResults(SearchResult searchResult){
        getQueue().add(searchResult);
    }

    @Override
    protected void awaitTasksDone() {
        do{
            processQueuedResults(getInput().size() * wordToFind.length);
            try {
                exitBarrier.await();
            } catch (InterruptedException e) {
                System.out.printf("await() interrupted");
            }
        }while (advanceTaskToNextCycle());

        super.awaitTasksDone();
    }

    private void processQueuedResults(final int resultCount) {
        Runnable processQueuedResultRunnable =
            new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i< resultCount; ++i){
                        try {

                            SearchResult result = getQueue().take();
                            result.print();
                        } catch (InterruptedException e) {
                            System.out.println("run() interrupted!");
                        }
                    }
                }
            };

        Thread t = new Thread(processQueuedResultRunnable);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("processQueuedResults() interrupted!");
        }

    }

    protected BlockingQueue<SearchResult> setQueue(BlockingQueue<SearchResult> q){
        BlockingQueue<SearchResult> old = resultQueue;
        resultQueue = q;
        return  old;

    }

    protected BlockingQueue<SearchResult> getQueue(){
        return resultQueue;
    }
}
