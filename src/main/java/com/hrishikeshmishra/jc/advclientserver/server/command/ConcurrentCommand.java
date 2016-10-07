package com.hrishikeshmishra.jc.advclientserver.server.command;


import com.hrishikeshmishra.jc.advclientserver.server.ConcurrentServer;
import com.hrishikeshmishra.jc.clientserver.server.ParallelCache;
import com.hrishikeshmishra.jc.clientserver.server.commands.Command;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class ConcurrentCommand extends Command
        implements Comparable<ConcurrentCommand>,  Runnable {

    private Socket socket;
    private String userName;
    private byte priority;


    public ConcurrentCommand(String[] command, Socket socket) {
        super(command);
        this.userName = command[1];
        this.priority = Byte.parseByte(command[2]);
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return userName;
    }

    public byte getPriority() {
        return priority;
    }

    public abstract String execute();


    @Override
    public int compareTo(ConcurrentCommand o) {
        return Byte.compare(o.getPriority(), this.getPriority());
    }

    @Override
    public void run() {
        String message = "Running a Task: " + userName
                + "; Priority" + priority;

        com.hrishikeshmishra.jc.clientserver.server.Logger.sendMessage(message);
        String ret = null;
        try {
            ret = execute();
        }catch (Throwable t){
            System.out.println(t);
        }

        ParallelCache cache = ConcurrentServer.getCache();

        if(isCacheable()){
            cache.put(String.join(";", command), ret);
        }

        try{
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(ret);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ret);
    }
}
