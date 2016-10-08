package com.hrishikeshmishra.jc.periodictasks;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        NewsSystem system = new NewsSystem("data/sources.txt");
        Thread t = new Thread(system);
        t.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.shutdown();
    }


}
