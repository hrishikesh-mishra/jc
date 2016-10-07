package com.hrishikeshmishra.jc.advclientserver.server.command.impl;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.clientserver.server.ConcurrentServer;

import java.net.Socket;


public class ConcurrentStopCommand extends ConcurrentCommand {

    public ConcurrentStopCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        ConcurrentServer.shutdown();
        return "Sever Stopped";
    }
}

