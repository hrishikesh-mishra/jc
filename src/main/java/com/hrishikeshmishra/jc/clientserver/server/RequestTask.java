package com.hrishikeshmishra.jc.clientserver.server;

import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent.ConcurrentErrorCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent.ConcurrentQueryCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent.ConcurrentReportCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent.ConcurrentStatusCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.concurrent.ConcurrentStopCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class RequestTask implements Runnable{
    private final Socket clientSocket;

    public RequestTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream())){
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line = in.readLine();

            Logger.sendMessage(line);
            ParallelCache  cache = ConcurrentServer.getCache();
            String ret =  cache.get(line);

            if(ret == null){
                Command command;
                String [] commandData  =  line.split(";");
                System.out.println("Command : " + commandData[0]);

                switch (commandData[0]){
                    case "q":
                        System.out.println("Query");
                        command = new ConcurrentQueryCommand(commandData);
                        break;
                    case "r":
                        System.out.println("Report");
                        command = new ConcurrentReportCommand(commandData);
                        break;

                    case "s":
                        System.out.println("Status");
                        command = new ConcurrentStatusCommand(commandData);
                        break;
                    case "z":
                        System.out.println("Stop");
                        command = new ConcurrentStopCommand(commandData);

                        break;
                    default:
                        System.out.println("Error");
                        command = new ConcurrentErrorCommand(commandData);
                }

                ret = command.execute();
                if(command.isCacheable()){
                    cache.put(line, ret);
                }
            } else {
                Logger.sendMessage("Command " + line + " was found in the cache");
            }

            System.out.println(ret);
            out.println(ret);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
