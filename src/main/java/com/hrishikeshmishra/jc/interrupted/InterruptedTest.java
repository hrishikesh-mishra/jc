package com.hrishikeshmishra.jc.interrupted;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class InterruptedTest {

    public static void main(String[] args) {
        System.out.printf("Entering main() ");

        DaemonRunnable runnable = new DaemonRunnable();
        Thread t = new Thread(runnable);

        t.start();

        try {
            Thread.sleep(2000);
            System.out.println("Interrupting thread ");
            t.interrupt();

            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println("Leaving main()");
    }
}
