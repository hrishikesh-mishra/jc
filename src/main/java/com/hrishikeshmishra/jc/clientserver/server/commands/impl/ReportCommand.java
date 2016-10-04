package com.hrishikeshmishra.jc.clientserver.server.commands.impl;

import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

public class ReportCommand extends Command{

    public ReportCommand(String[] command) {
        super(command);
    }

    @Override
    public String execute() {
        WDIDAO dao = WDIDAO.getDAO();
        return dao.report(command[1]);
    }
}
