package com.hrishikeshmishra.jc.taskgang;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public class OneShotThreadPerTask extends SearchTaskGangCommon {

    private List<Thread> workerThreads;

    public OneShotThreadPerTask(String[] wordToFind, String[][] inputIterator) {
         super(wordToFind, inputIterator);
        workerThreads = new LinkedList<Thread>();
    }

    protected void initiateTaskGang(int inputSize) {

        if(getExecutor() == null){

            setExecutor(new Executor() {

                public void execute(Runnable command) {
                    Thread thread = new Thread(command);
                    workerThreads.add(thread);
                    thread.start();
                }

            });
        }

        for(int i=0; i< inputSize; i++){
            getExecutor().execute(makeTask(i));
        }
    }

    protected boolean processInput(String inputData) {
        for (String word : wordToFind){
            SearchResult result = searchForWord(word, inputData);

            synchronized (System.out){
                result.print();
            }
        }

        return true;
    }

    protected void awaitTasksDone(){
        for (Thread thread : workerThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("awaitTasksDone interrupted");
            }
        }
    }
}
