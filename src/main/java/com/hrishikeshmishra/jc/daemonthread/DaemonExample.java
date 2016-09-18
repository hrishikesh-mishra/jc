package com.hrishikeshmishra.jc.daemonthread;

import java.util.Random;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class DaemonExample extends Thread {
    private final boolean isDaemon;
    private final int MAX_ITERATION = 100000000;

    public DaemonExample(boolean isDaemon) {
        this.isDaemon = isDaemon;
        if (isDaemon) setDaemon(true);
    }

    private int computeGCD(int number1, int number2){
        if(number2 == 0) return number1;
        return computeGCD(number2, number1 % number2);
    }

    public void run(){
        String threadString  = " with "
                + ( (!isDaemon)?"UserThread" : "DaemonThread")
                + " thread id : "
                + Thread.currentThread();

        System.out.println("Entering run () " + threadString);
        Random random = new Random();

        try {
            for (int i = 0; i< MAX_ITERATION; ++i){
                int number1 = random.nextInt();
                int number2 = random.nextInt();

                if (i % 10000000 == 0){
                    System.out.println("In run() " + threadString
                    + " the GCD of  " + number1 +
                    " and " + number2 + " = " + computeGCD(number1, number2));
                }
            }
        }
        finally {
            System.out.println("Leaving run()");
        }

    }




}
