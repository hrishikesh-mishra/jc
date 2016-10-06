package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;


import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

public class ConcurrentErrorCommand extends Command{

    public ConcurrentErrorCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        return "Unknown command: " + command[0];
    }
}
