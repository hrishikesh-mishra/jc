package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;

import com.hrishikeshmishra.jc.clientserver.server.ParallelCache;

import java.util.concurrent.TimeUnit;

public class CleanCacheTask implements Runnable {

    private ParallelCache cache;

    public CleanCacheTask(ParallelCache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        try
        {
            while (!Thread.currentThread().isInterrupted()){
                TimeUnit.SECONDS.sleep(10);
                cache.cleanCache();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
