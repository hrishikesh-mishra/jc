package com.hrishikeshmishra.jc.advclientserver.server;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentCancelCommand;
import com.hrishikeshmishra.jc.clientserver.server.Logger;
import com.hrishikeshmishra.jc.clientserver.server.ParallelCache;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class ConcurrentServer {

    private static ParallelCache cache;
    private static volatile boolean isStopped = false;
    private static LinkedBlockingQueue<Socket> pendingConnections;
    private static ConcurrentHashMap<String, ConcurrentMap<ConcurrentCommand, ServerTask<?>>> taskController;
    private static Thread requestThread;
    private static RequestTask task;
    private static ServerSocket serverSocket;



    public static void main(String[] args) throws IOException {
        cache = new ParallelCache();
        Logger.initializeLog();
        pendingConnections = new LinkedBlockingQueue<>();
        taskController = new ConcurrentHashMap<>();
        task = new RequestTask(pendingConnections, taskController);
        requestThread = new Thread(task);
        requestThread.start();

        System.out.println("Initializaiton completed.");
        Logger.sendMessage("Writing log");

        serverSocket = new ServerSocket(9070);
        do {
            try {
                Socket clientSocket = serverSocket.accept();
                pendingConnections.put(clientSocket);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!isStopped);


        finishServer();
        System.out.println("Shutting down cache");
        cache.shutdown();
        System.out.println("Cache Ok " + new Date());
    }

    private static void finishServer() {
        System.out.println("Shutting down server ");
        task.shutdown();
        System.out.println("Shutting down request task");
        requestThread.interrupt();
        System.out.println("Request task ok ");
        System.out.println("Closing socket ");
        System.out.println("Shutting down logger");
        System.out.println("Logger shuting down");
        Logger.shutdown();
        System.out.println("Logger Ok");
        System.out.println("Main thread ended");
    }

    public static void shutdown(){
        isStopped = true;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cancelTask(String username){
        ConcurrentMap <ConcurrentCommand, ServerTask<?>> userTasks =
                taskController.get(username);
        if(Objects.isNull(userTasks))
            return;

        int taskNumber = 0;
        Iterator<ServerTask<?>> it = userTasks.values().iterator();

        while (it.hasNext()){
            ServerTask<?> task = it.next();
            ConcurrentCommand command = task.getCommand();

            if(!(command instanceof ConcurrentCancelCommand) &&
                    task.cancel(true)) {
                taskNumber ++;
                Logger.sendMessage("Task with code " +
                command.hashCode() + " cancelled: " + command.getClass().getSimpleName());
                it.remove();
            }
        }

        String message = taskNumber + " tasks has been cancelled ";
        Logger.sendMessage(message);

    }


    public static void finishTask(String username, ConcurrentCommand command){
        ConcurrentMap<ConcurrentCommand ,  ServerTask<?>> userTasks = taskController.get(username);
        userTasks.remove(command);

        String message = "Task with code " + command.hashCode() + " has finished ";
        Logger.sendMessage(message);
    }

    public static ParallelCache getCache() {
        return cache;
    }

    public static ThreadPoolExecutor getExecutor() {
        return task.getExecutor();
    }
}
