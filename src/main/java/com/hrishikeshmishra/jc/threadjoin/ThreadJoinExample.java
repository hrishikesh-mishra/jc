package com.hrishikeshmishra.jc.threadjoin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by hrishikesh.mishra
 */
public class ThreadJoinExample {
    private final static boolean diagnosticsEnabled = true;
    private final static String [] oneShotInputString = {
            "xreo",
            "xfao",
            "xmiomio",
            "xlao",
            "xtiotio",
            "xsoosoo",
            "xdoo",
            "xdoodoo"
    };

    private final static String [] wordList = {
            "do",
            "re",
            "mi",
            "fa",
            "so",
            "la",
            "ti",
            "do"
    };

    private static class SearchOneShotThreadJoin {
        private volatile List<String> strings = null;
        private final String[] wordsToFind;
        private List<Thread> workerThreads;

        public SearchOneShotThreadJoin(String [] wordsToFind,
                                       String [] inputStrings){
            this.wordsToFind = wordsToFind;
            this.strings = Arrays.asList(inputStrings);
            this.workerThreads = new LinkedList<Thread>();

            for (int i=0; i< strings.size(); i++){
                Thread t = new Thread(makeTask(i));
                workerThreads.add(t);
                t.start();
            }

            for (Thread thread: workerThreads){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    printDebugging("join() interrupted");
                }
            }
        }

        private Runnable makeTask(final int index){
            return new Runnable() {
                public void run() {
                    String element = strings.get(index);
                    if(processInput(element) == false) return;
                }
            };
        }

        private boolean processInput(String inputData ){
            for (String word: wordsToFind)
                for(int i = inputData.indexOf(word, 0); i != -1; i = inputData.indexOf(word, i + word.length()))
                    processResult(" in thread "
                                + Thread.currentThread().getId()
                                + " "
                                + word
                                + " was found af offeset "
                                + i
                                + " in string "
                                + inputData
                            );


            return true;
        }

        private void processResult(String result){
            printDebugging(result);
        }
    }

    static void printDebugging(String output){
        if (diagnosticsEnabled)
            System.out.println(output);
    }

    public static void main(String[] args) {
        printDebugging("Starting ThreadJoinExample");
        printDebugging("Starting JOIN");
        new SearchOneShotThreadJoin(wordList, oneShotInputString);
        printDebugging("Ending JOIN");
        printDebugging("Ending ThreadJoinTest");
    }
}
