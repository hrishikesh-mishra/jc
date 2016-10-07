package com.hrishikeshmishra.jc.advclientserver.server.command.impl;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.advclientserver.server.ConcurrentServer;
import com.hrishikeshmishra.jc.clientserver.server.Logger;

import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;


public class ConcurrentStatusCommand extends ConcurrentCommand {

    public ConcurrentStatusCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        ThreadPoolExecutor executor = ConcurrentServer.getExecutor();

        sb.append("Server status; ")
                .append("Actived Threads: ")
                .append(String.valueOf(executor.getActiveCount()))
                .append(";")
                .append("Maximum pool size: ")
                .append(String.valueOf(executor.getMaximumPoolSize()))
                .append(";")
                .append("Core Pool Size: ")
                .append(String.valueOf(executor.getCorePoolSize()))
                .append(";")
                .append("Pool size: ")
                .append(String.valueOf(executor.getPoolSize()))
                .append(";")
                .append("Largest Pool Size: ")
                .append(String.valueOf(executor.getLargestPoolSize()))
                .append(";")
                .append("Complete Task count")
                .append(String.valueOf(executor.getCompletedTaskCount()))
                .append(";")
                .append("Task count: ")
                .append(String.valueOf(executor.getTaskCount()))
                .append(";")
                .append("Queue Size: ")
                .append(String.valueOf(executor.getQueue().size()))
                .append(";");
        Logger.sendMessage(sb.toString());

        return sb.toString();

    }
}
