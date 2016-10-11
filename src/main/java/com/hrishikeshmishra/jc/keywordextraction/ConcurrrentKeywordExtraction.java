package com.hrishikeshmishra.jc.keywordextraction;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class ConcurrrentKeywordExtraction {

    public static void main(String[] args) {
        Date start, end;

        ConcurrentHashMap<String, Word> globalVoc = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, AtomicInteger> globalKeyword = new ConcurrentHashMap<>();

        start = new Date();
        File source = new File("data/indexing");
        File[] files = source.listFiles();

        ConcurrentLinkedDeque<File> phase1 = new ConcurrentLinkedDeque<>(Arrays.asList(files));
        ConcurrentLinkedDeque<File> phase2 = new ConcurrentLinkedDeque<>(Arrays.asList(files));


        int numDocuments = files.length;
        int factor = 1;
        int numTasks =factor*  Runtime.getRuntime().availableProcessors();
        Phaser phaser = new Phaser();

        Thread [] threads = new Thread [numTasks];
        KeywordExtractionTask[] tasks =  new KeywordExtractionTask[numTasks];

        for(int  i=0 ; i< numTasks; i++){
            tasks[i] = new KeywordExtractionTask(globalVoc, globalKeyword, phase1, phase2, phaser, "Task-" + i, i==0, phase1.size());
            phaser.register();
            System.out.println(phaser.getRegisteredParties() + " tasks arrived to Phaser");
        }

        for(int i=0; i< numTasks; i++){
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        for(int i=0; i< numTasks; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Is Terminated:  " + phaser.isTerminated());
        end = new Date();
        System.out.println("Execution time: " + (end.getTime() - start.getTime()));
        System.out.println("Vocabulary size :" + globalVoc.size());
        System.out.println("Number of documents : "+ numDocuments);

    }
}
