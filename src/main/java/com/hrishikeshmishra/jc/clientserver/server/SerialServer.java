package com.hrishikeshmishra.jc.clientserver.server;


import com.hrishikeshmishra.jc.clientserver.server.commands.Command;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.ErrorCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.QueryCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.ReportCommand;
import com.hrishikeshmishra.jc.clientserver.server.commands.impl.StopCommand;
import com.hrishikeshmishra.jc.clientserver.server.dao.WDIDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {

    public static void main(String[] args) {
        WDIDAO dao = WDIDAO.getDAO();
        boolean stopServer = false;
        System.out.println("Initialization completed");

        try(ServerSocket serverSocket = new ServerSocket(9090)){

            do {

                try(Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String line = in.readLine();
                    Command command;
                    String [] commandData  =  line.split(";");
                    System.out.println("Command : " + commandData[0]);

                    switch (commandData[0]){
                        case "q":
                            System.out.println("Query");
                            command = new QueryCommand(commandData);
                            break;
                        case "r":
                            System.out.println("Report");
                            command = new ReportCommand(commandData);
                            break;
                        case "z":
                            System.out.println("Stop");
                            command = new StopCommand(commandData);
                            stopServer = true;
                            break;
                        default:
                            System.out.println("Error");
                            command = new ErrorCommand(commandData);
                    }

                    String response = command.execute();
                    System.out.println(response);
                    out.println(response);
                }
            } while (!stopServer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
