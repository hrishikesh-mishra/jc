package com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent;

import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

public class ConcurrentReportCommand extends Command{

    protected boolean cacheable = true;

    public ConcurrentReportCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        WDIDAO dao = WDIDAO.getDAO();
        return dao.report(command[1]);
    }
}
