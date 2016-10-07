package com.hrishikeshmishra.jc.advclientserver.server;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;

import java.util.concurrent.FutureTask;

/**
 * Created by hrishikesh.mishra on 09/10/16.
 */
public class ServerTask<V> extends FutureTask<V>  implements Comparable<ServerTask<V>>{

    private ConcurrentCommand command;

    public ServerTask(Runnable command) {
        super(command, null);
        this.command = (ConcurrentCommand) command;
    }

    public ConcurrentCommand getCommand() {
        return command;
    }

    public void setCommand(ConcurrentCommand command) {
        this.command = command;
    }

    @Override
    public int compareTo(ServerTask<V> o) {
        return command.compareTo(o.getCommand());
    }
}
