package com.hrishikeshmishra.jc.advclientserver.server.command.impl;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.clientserver.server.Logger;

import java.net.Socket;

public class ConcurrentCancelCommand extends  ConcurrentCommand {

    public ConcurrentCancelCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        String message = "The task of user " + getUsername()
                + " has been cancelled.";

        Logger.sendMessage(message);
        return message;
    }
}
