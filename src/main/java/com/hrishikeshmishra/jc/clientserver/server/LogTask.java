package com.hrishikeshmishra.jc.clientserver.server;


import java.util.concurrent.TimeUnit;

public class LogTask implements Runnable {

    @Override
    public void run() {

        try{
            while (Thread.currentThread().isInterrupted()){
                TimeUnit.SECONDS.sleep(10);
                Logger.writeLogs();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Logger.writeLogs();
    }
}
