package com.hrishikeshmishra.jc.taskgang;

/**
 * Created by hrishikesh.mishra on 19/09/16.
 */
public class TaskGangTest {

    public enum  TestsToRun{
        ONESHOT_THREAD_TO_RUN,
        ONESHOT_EXECUTOR_SERVICE,
        ONESHOT_EXECUTOR_SERVICE_FUTURE,
//        ONESHOT_EXECUTOR_COMPLETION_FUTURE
    }

    private final static String [] wordList = {
            "do",
            "re",
            "mi",
            "fa",
            "so",
            "la",
            "ti",
            "do"
    };

    private final static String [][] oneShotInputString = {
            {   "xreo",
                "xfao",
                "xmiomio",
                "xlao",
                "xtiotio",
                "xsoosoo",
                "xdoo",
                "xdoodoo"
            }
    };

    private final static boolean diagnosticsEnabled = true;

    private static void printDebugging(String output){
        if (diagnosticsEnabled)
            System.out.println(output);
    }

    private static Runnable makeTaskGang(String [] wordList, TestsToRun choice){
        switch (choice){
            case ONESHOT_THREAD_TO_RUN:
                return new OneShotThreadPerTask(wordList, oneShotInputString);
            case ONESHOT_EXECUTOR_SERVICE:
                return new OneShotExecutorService(wordList, oneShotInputString);
            case ONESHOT_EXECUTOR_SERVICE_FUTURE:
                return new OneShotExecutorServiceFuture(wordList, oneShotInputString);
//            case ONESHOT_EXECUTOR_COMPLETION_FUTURE:
//                //return new OneShotExecutorCompletionService(wordList, oneShotInputString);
        }
        return null;
    }


    public static void main(String[] args) {
        printDebugging("Starting TaskGangTest");

//        for (TestsToRun test: TestsToRun.values()){
            TestsToRun test = TestsToRun.ONESHOT_EXECUTOR_SERVICE_FUTURE;
            printDebugging("Starting " + test);
            makeTaskGang(wordList, test).run();
            printDebugging("Ending " + test);
        //}

        printDebugging("Ending TaskGangTest");
    }



}
