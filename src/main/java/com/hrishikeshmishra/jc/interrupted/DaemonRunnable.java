package com.hrishikeshmishra.jc.interrupted;

import java.util.Random;

/**
 * Created by hrishikesh.mishra on 18/09/16.
 */
public class DaemonRunnable extends Random implements Runnable {

    private final int MAX_ITERATION = 100000000;



    private int computeGCD(int number1, int number2){
        if(number2 == 0) return number1;
        return computeGCD(number2, number1 % number2);
    }

    public void run(){
        String threadString  = " with "
                + " thread id : "
                + Thread.currentThread();

        System.out.println("Entering run () " + threadString);

       try {
            for (int i = 0; i< MAX_ITERATION; ++i){
                int number1 = nextInt();
                int number2 = nextInt();

                if (Thread.interrupted())
                    throw new InterruptedException();

                if (i % 10000000 == 0){
                    System.out.println("In run() " + threadString
                            + " the GCD of  " + number1 +
                            " and " + number2 + " = " + computeGCD(number1, number2));
                }
            }
        } catch (InterruptedException e) {
           System.out.println("Thread interrupted " + threadString);

       } finally {
            System.out.println("Leaving run()");
        }

    }

}
