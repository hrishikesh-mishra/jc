package com.hrishikeshmishra.jc.clientserver.server.commands;

public abstract class Command {
    protected String [] command;
    protected boolean cacheable = false;

    public Command(String[] command) {
        this.command = command;
    }

    public abstract String execute();

    public boolean isCacheable() {
        return cacheable;
    }
}
