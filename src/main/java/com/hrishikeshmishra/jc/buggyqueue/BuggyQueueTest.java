package com.hrishikeshmishra.jc.buggyqueue;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class BuggyQueueTest {
    public static void main(String[] args) {

        final SimpleQueue<String> sQue = new SimpleQueue<String>();
        final  int maxIterations = 10000000;

        Thread producer = new Thread(new Runnable() {
            public void run() {
                for (int i =0; i < maxIterations; i++){
                    try {
                        sQue.put(Integer.toString(i));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            public void run() {
                for (int i =0; i < maxIterations; i++){
                    try {
                        sQue.take();
                        //System.out.println(sQue.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
