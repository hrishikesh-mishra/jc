package com.hrishikeshmishra.jc.clientserver.server.commands;

public abstract class Command {
    protected String [] command;

    public Command(String[] command) {
        this.command = command;
    }

    public abstract String execute();
}
