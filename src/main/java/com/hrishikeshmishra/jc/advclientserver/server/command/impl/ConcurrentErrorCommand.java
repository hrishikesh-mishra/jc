package com.hrishikeshmishra.jc.advclientserver.server.command.impl;


import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;

import java.net.Socket;

public class ConcurrentErrorCommand extends ConcurrentCommand {

    public ConcurrentErrorCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        return "Unknown command: " + command[3];
    }
}
