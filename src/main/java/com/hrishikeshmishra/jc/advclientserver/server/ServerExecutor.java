package com.hrishikeshmishra.jc.advclientserver.server;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.clientserver.server.Logger;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerExecutor extends ThreadPoolExecutor{

    private ConcurrentHashMap<Runnable, Date> startTimes;
    private ConcurrentHashMap<String, ExecutorStatistics> executorStatistics;

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final long KEEP_ALIVE_TIME = 10;

    private static final RejectedTaskController REJECTED_TASK_CONTROLLER = new RejectedTaskController();


    public ServerExecutor(){
        super(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new PriorityBlockingQueue<>(), REJECTED_TASK_CONTROLLER);
        startTimes = new ConcurrentHashMap<>();
       executorStatistics = new ConcurrentHashMap<>();
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        startTimes.put(r, new Date());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        ServerTask<?> task = (ServerTask<?>) r;
        ConcurrentCommand command = task.getCommand();

        if(Objects.isNull(t)){
            if(!task.isCancelled()){
                Date startDate = startTimes.remove(r);
                Date endDate = new Date();
                long executionTime = endDate.getTime() - startDate.getTime();

                ExecutorStatistics statistics = executorStatistics.computeIfAbsent(command.getUsername(),
                        n -> new ExecutorStatistics());
                statistics.addExecutionTime(executionTime);
                statistics.addTasks();
                ConcurrentServer.finishTask(command.getUsername(), command);
            } else  {
                String message = "The task " + command.hashCode() + " of user " +
                            command.getUsername() + " has been cancelled";
                Logger.sendMessage(message);
            }
        }else {
            String message = "The execution " + t.getMessage() + " has been thrown. ";
            Logger.sendMessage(message);
        }
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new ServerTask<T>(runnable);
    }

    public void writeStatistics(){
        for(Map.Entry<String, ExecutorStatistics> entry : executorStatistics.entrySet()){
            String user = entry.getKey();
            ExecutorStatistics statistics = entry.getValue();
            Logger.sendMessage(user + " : " + statistics );
        }
    }

}
