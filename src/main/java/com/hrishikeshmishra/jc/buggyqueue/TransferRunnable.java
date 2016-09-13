package com.hrishikeshmishra.jc.buggyqueue;

/**
 * Created by hrishikesh.mishra
 */
public class TransferRunnable implements Runnable {

    private SimpleQueue<String> mAQueue;
    private SimpleQueue<String> mBQueue;
    private int mIteration;

    public TransferRunnable(SimpleQueue<String> mAQueue, SimpleQueue<String> mBQueue, int mIteration) {
        this.mAQueue = mAQueue;
        this.mBQueue = mBQueue;
        this.mIteration = mIteration;
    }

    public void run() {
        for (int i =0; i< mIteration; ++i){
            try {
                TransferRunnable.transfer(mAQueue, mBQueue);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void transfer(SimpleQueue<String> src, SimpleQueue<String> dest) throws InterruptedException {
        synchronized (src){
            synchronized (dest){
                while (!src.isEmpty())
                    dest.put(src.take());
            }
        }
    }
}
