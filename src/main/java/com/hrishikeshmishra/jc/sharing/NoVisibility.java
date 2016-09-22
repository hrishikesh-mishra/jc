package com.hrishikeshmishra.jc.sharing;

/**
 * <p>
 *     NoVisibility could loop forever because the value of ready might
 *     never become visible to the reader thread. Even  more strangely,
 *     NoVisibility could print zero because the write to ready might
 *     be made visible to the reader thread  before the write to number,
 *     a phenomenon known as reordering. There is no guarantee that
 *     operations in one thread  will be performed in the order given by
 *     the program, as long as the reordering is not detectable from within
 *     that thread even if the reordering is apparent to other threads.
 *     
 *     
 *     In the absence of synchronization, the compiler, processor, and
 *     runtime can do some downright weird things to the order in which
 *     operations appear to execute. Attempts to reason about the order
 *     in which memory actions "must" happen in insufficiently synchronized
 *     multithreaded programs will almost certainly be incorrect.
 * </p>
 * Created by hrishikesh.mishra on 20/09/16.
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}