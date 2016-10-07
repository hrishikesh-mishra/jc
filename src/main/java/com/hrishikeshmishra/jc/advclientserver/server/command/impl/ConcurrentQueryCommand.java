package com.hrishikeshmishra.jc.advclientserver.server.command.impl;


import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

import java.net.Socket;

public class ConcurrentQueryCommand extends ConcurrentCommand {

    public ConcurrentQueryCommand(String[] command, Socket socket) {
        super(command, socket);
    }

    @Override
    public String execute() {
        WDIDAO dao = WDIDAO.getDAO();
        if(command.length == 5){
            return dao.query(command[3], command[4]);
        }else  if (command.length == 6){
            return dao.query(command[3], command[4], Short.parseShort(command[5]));
        } else {
            return "ERROR; Bad Command";
        }
    }
}
