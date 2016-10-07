package com.hrishikeshmishra.jc.advclientserver.server;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutorStatistics {

    private AtomicLong executionTime = new AtomicLong(0L);
    private AtomicInteger numTasks = new AtomicInteger(0);

    public void addExecutionTime (long time){
        executionTime.addAndGet(time);
    }

    public void addTasks(){
        numTasks.incrementAndGet();
    }

    public AtomicLong getExecutionTime() {
        return executionTime;
    }

    public AtomicInteger getNumTasks() {
        return numTasks;
    }

    @Override
    public String toString() {
        return "Executed Tasks :"  + getNumTasks() + ". Execution Time" +getExecutionTime();
    }
}
