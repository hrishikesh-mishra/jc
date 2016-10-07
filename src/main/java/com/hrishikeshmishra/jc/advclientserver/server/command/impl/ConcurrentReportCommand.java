package com.hrishikeshmishra.jc.advclientserver.server.command.impl;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

import java.net.Socket;


public class ConcurrentReportCommand extends ConcurrentCommand  {

    public ConcurrentReportCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        WDIDAO dao = WDIDAO.getDAO();
        return dao.report(command[3]);
    }
}
