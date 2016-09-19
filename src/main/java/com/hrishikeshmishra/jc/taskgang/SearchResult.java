package com.hrishikeshmishra.jc.taskgang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public class SearchResult {

    public class Result{

        public int index;

        public Result(int index) {
            this.index = index;
        }
    }

    public long threadId;

    public String word;

    public String inputData;

    public long cycle;

    protected List<Result> list;

    public SearchResult(){
        list = null;
    }

    public SearchResult(long threadId,
                        long cycle,
                        String word,
                        String inputData){
        this.threadId = threadId;
        this.cycle = cycle;
        this.word = word;
        this.inputData = inputData;
        list = new ArrayList<Result>();
    }

    @Override
    public String toString() {
        return "["
                + threadId
                + "|"
                + word
                + "] "
                + word
                + " at "
                + inputData ;
    }

    public void add(int index){
        list.add(new Result(index));
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void print(){
        if(!isEmpty()){
            System.out.print(toString());
            for (Result result : list) {
                System.out.print("["
                                   + result.index
                                + "]");
            }

            System.out.println("");
        }
    }
}
