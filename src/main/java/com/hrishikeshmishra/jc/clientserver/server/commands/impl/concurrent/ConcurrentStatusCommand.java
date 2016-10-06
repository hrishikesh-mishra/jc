package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;

import com.hrishikeshmishra.jc.clientserver.server.ConcurrentServer;
import com.hrishikeshmishra.jc.clientserver.server.Logger;
import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by hrishikesh.mishra on 08/10/16.
 */
public class ConcurrentStatusCommand extends Command {

    public ConcurrentStatusCommand(String[] command) {
        super(command);
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
