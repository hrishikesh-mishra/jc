package com.hrishikeshmishra.jc.buggyqueue;

/**
 * Created by hrishikesh.mishra
 */
public class DeadlockTest {

    public static void main(String[] args) throws InterruptedException {

        SimpleQueue<String> aQueue = new SimpleQueue<String>();
        SimpleQueue<String> bQueue = new SimpleQueue<String>();
        int iteration = 10;

        for (int i=1; i<= iteration; ++i){
            aQueue.put(Integer.toString(i));
        }

        Thread transfer1 = new Thread(new TransferRunnable(aQueue, bQueue, iteration));
        Thread transfer2 = new Thread(new TransferRunnable(bQueue, aQueue, iteration));

        transfer1.start();
        transfer2.start();

    }
}
