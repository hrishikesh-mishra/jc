package com.hrishikeshmishra.jc.keywordextraction;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @TODO   THIS CODE HAS SOME ISSUE
 * Created by hrishikesh.mishra on 11/10/16.
 */
public class KeywordExtractionTask implements Runnable {

    private ConcurrentHashMap<String, Word> globalVoc;
    private ConcurrentHashMap<String, AtomicInteger> globalKeywords;
    private ConcurrentLinkedDeque<File> phase1;
    private ConcurrentLinkedDeque<File> phase2;

    private Phaser phaser;
    private String name;
    private boolean main;

    private int parsedDocuments;
    private int numDocuments;

    public KeywordExtractionTask(ConcurrentHashMap<String, Word> globalVoc, ConcurrentHashMap<String, AtomicInteger> globalKeywords, ConcurrentLinkedDeque<File> phase1, ConcurrentLinkedDeque<File> phase2, Phaser phaser, String name, boolean main, int numDocuments) {
        this.globalVoc = globalVoc;
        this.globalKeywords = globalKeywords;
        this.phase1 = phase1;
        this.phase2 = phase2;
        this.phaser = phaser;
        this.name = name;
        this.main = main;
        this.numDocuments = numDocuments;
    }

    @Override
    public void run() {
        File file;

        //Phase 1
        phaser.arriveAndAwaitAdvance();
        System.out.println(name +  ": Phase 1");
        while ((file = phase1.poll()) != null){
            Document doc = DocumentParser.parse(file.getAbsolutePath());
            for (Word word: doc.getVoc().values()){
                globalVoc.merge(word.getWord(), word, Word::merge);
            }
            parsedDocuments++;
        }

        System.out.println(name + ":" + parsedDocuments + " parsed.");
        phaser.arriveAndAwaitAdvance();


        //Phase 2
        System.out.println(name + " : Phase 2");
        while ((file= phase2.poll()) != null){
            Document doc = DocumentParser.parse(file.getAbsolutePath());
            List<Word> keywords = new ArrayList<>(doc.getVoc().values());

            for (Word word: keywords){
                Word globalWord = globalVoc.get(word);
                word.setDf(globalWord.getDf(), numDocuments);
            }

            Collections.sort(keywords);

            if(keywords.size() > 10) keywords = keywords.subList(0, 10);

            for (Word word: keywords){
                addKeyword(globalKeywords, word.getWord());
            }
        }

        System.out.println(name + ": " + parsedDocuments + " parsed.");


        if(main){
            phaser.arriveAndAwaitAdvance();

            Iterator<Map.Entry<String, AtomicInteger>> iterator = globalKeywords.entrySet().iterator();
            Keyword [] orderedGlobalKeyword = new Keyword[globalKeywords.size()];
            int index = 0;
            while (iterator.hasNext()){
                Map.Entry<String, AtomicInteger> entry = iterator.next();
                Keyword keyword = new Keyword(entry.getKey(), entry.getValue().get());
                orderedGlobalKeyword[index] = keyword;
                index++;
            }

            System.out.println("Keyword size:" + orderedGlobalKeyword.length);
            Arrays.parallelSort(orderedGlobalKeyword);

            int  counter =0 ;
            for(int i=0 ; i < orderedGlobalKeyword.length; i++){
                Keyword keyword = orderedGlobalKeyword[i];
                System.out.println(keyword.getWord() + ": " + keyword.getDf());
                counter++;
                if(counter == 100)
                    break;
            }
        }

        phaser.arriveAndDeregister();
        System.out.println("Thread: " + name + " has finished.");

    }

    private void addKeyword(ConcurrentHashMap<String, AtomicInteger> globalKeywords, String word) {
        globalKeywords.merge(word,  new AtomicInteger(1), (y, x) -> new AtomicInteger(x.addAndGet(y.get())));
    }
}
