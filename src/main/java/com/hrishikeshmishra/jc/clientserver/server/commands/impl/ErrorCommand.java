package com.hrishikeshmishra.jc.clientserver.server.commands.impl;


import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

public class ErrorCommand extends Command{

    public ErrorCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        return "Unknown command: " + command[0];
    }
}
