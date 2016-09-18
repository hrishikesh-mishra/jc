package com.hrishikeshmishra.jc.daemonthread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class ExecutorTest {

    public static void main(String[] args) {

        System.out.printf("Entering main() ");

        /** Change false to true for daemon mode **/
        final boolean isDaemon = false;

        DaemonRunnable daemonRunnable = new DaemonRunnable(isDaemon);
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(isDaemon);
                return thread;
            }
        };

        final int POOL_SIZE = 2;
        final Executor executor = Executors.newFixedThreadPool(POOL_SIZE, threadFactory);
        for (int i=0 ;i < POOL_SIZE; i++)
            executor.execute(daemonRunnable);

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        System.out.println("Leaving main() ");


    }
}
