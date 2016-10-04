package com.hrishikeshmishra.jc.clientserver.server.commands.impl;


import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

public class StopCommand extends Command{

    public StopCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        return "Sever Stopped";
    }
}
