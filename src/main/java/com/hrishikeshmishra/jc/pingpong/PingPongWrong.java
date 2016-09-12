package com.hrishikeshmishra.jc.pingpong;

/**
 * Created by hrishikesh.mishra
 */
public class PingPongWrong {
    public static int mMaxIteration = 10;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Ready...Set...Go!");

        PingPongWrongThread ping = new PingPongWrongThread("Ping!");
        PingPongWrongThread pong = new PingPongWrongThread("Pong!");

        ping.start();
        pong.start();

        ping.join();
        pong.join();

        System.out.printf("Done!");
    }

    public static class PingPongWrongThread extends Thread  {

        private String mStringToPrint;

        public PingPongWrongThread(String mStringToPrint) {
            this.mStringToPrint = mStringToPrint;
        }

        public void run() {
            for(int loopsDone =1; loopsDone < mMaxIteration; ++loopsDone){
                System.out.println(mStringToPrint + "(" + loopsDone + ")");
            }
        }
    }


}
