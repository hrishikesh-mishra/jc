package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;


import com.hrishikeshmishra.jc.clientserver.server.ConcurrentServer;
import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

public class ConcurrentStopCommand extends Command{

    public ConcurrentStopCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        ConcurrentServer.shutdown();
        return "Sever Stopped";
    }
}
