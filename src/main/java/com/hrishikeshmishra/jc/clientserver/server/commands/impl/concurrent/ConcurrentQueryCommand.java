package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;

import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

public class ConcurrentQueryCommand extends Command{

    protected boolean cacheable = true;

    public ConcurrentQueryCommand(String[] command) {
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
