package com.hrishikeshmishra.jc.advclientserver.server;


import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentCancelCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentErrorCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentQueryCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentReportCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentStatusCommand;
import com.hrishikeshmishra.jc.advclientserver.server.command.impl.ConcurrentStopCommand;
import com.hrishikeshmishra.jc.clientserver.server.Logger;
import com.hrishikeshmishra.jc.clientserver.server.ParallelCache;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class RequestTask implements Runnable {

    private LinkedBlockingQueue<Socket> pendingConnections;
    private ServerExecutor executor = new ServerExecutor();
    private ConcurrentMap<String, ConcurrentMap<ConcurrentCommand, ServerTask<?>>>
    tasksController;


    public RequestTask(LinkedBlockingQueue<Socket> pendingConnections, ConcurrentMap<String, ConcurrentMap<ConcurrentCommand, ServerTask<?>>> tasksController) {
        this.pendingConnections = pendingConnections;
        this.tasksController = tasksController;
    }

    @Override
    public void run() {


            while (!Thread.currentThread().isInterrupted()){
                try {
                    Socket clientSocket = pendingConnections.take();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String line = in.readLine();
                    Logger.sendMessage(line);

                    ConcurrentCommand command;
                    ParallelCache cache = ConcurrentServer.getCache();
                    String ret = cache.get(line);

                    if(Objects.isNull(ret)){
                        String [] commandData  = line.split(";");
                        System.out.println("Command : " + commandData[0]);

                        switch (commandData[0]){
                            case "q" :
                                System.out.printf("Query");
                                command = new ConcurrentQueryCommand(commandData, clientSocket);
                                break;
                            case "r" :
                                System.out.println("Report");
                                command = new ConcurrentReportCommand( commandData, clientSocket);
                                break;
                            case "s":
                                System.out.println("Status");
                                command = new ConcurrentStatusCommand( commandData, clientSocket);
                                break;
                            case "z":
                                System.out.println("Stop");
                                command = new ConcurrentStopCommand( commandData, clientSocket);
                                break;
                            case "c":
                                System.out.println("Cancel");
                                command = new ConcurrentCancelCommand( commandData, clientSocket);
                                break;
                            default:
                                System.out.println("Error");
                                command = new ConcurrentErrorCommand( commandData, clientSocket);
                                break;
                        }
                        ServerTask<?> controller = (ServerTask<?>) executor.submit(command);
                        storeController(command.getUsername(), controller, command);
                    }else {
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        out.println(ret);
                        clientSocket.close();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }

    private void storeController(String username, ServerTask<?> controller, ConcurrentCommand command) {
        tasksController.computeIfAbsent(username, k->new ConcurrentHashMap<>()).put(command, controller);
    }

    public void shutdown(){
        String  message = "Request task : " +
                pendingConnections.size()
                + " pending connections.";
        Logger.sendMessage(message);
    }

    public void terminate(){
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
            executor.writeStatistics();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ServerExecutor getExecutor() {
        return executor;
    }
}
