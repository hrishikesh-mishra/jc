package com.hrishikeshmishra.jc.clientserver.server.commands.impl;

import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

public class QueryCommand extends Command{

    public QueryCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        WDIDAO dao = WDIDAO.getDAO();

        if(command.length == 3){
            return dao.query(command[1], command[2]);
        }else if (command.length == 4){
            return dao.query(command[1], command[2], Short.parseShort(command[3]));
        } else  {
            return "ERROR; Bad Command";
        }

    }
}
