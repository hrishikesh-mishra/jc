package com.hrishikeshmishra.jc.daemonthread;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class DaemonExampleTest {

    public static void main(String[] args) {
        System.out.printf("Entering main() ");

        /** Change false to true for daemon mode **/
        boolean isDaemon = false;

        DaemonExample daemon = new DaemonExample(isDaemon);
        daemon.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println("Leaving main()");
    }
}
