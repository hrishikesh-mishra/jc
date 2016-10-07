package com.hrishikeshmishra.jc.advclientserver.server;

import com.hrishikeshmishra.jc.advclientserver.server.command.ConcurrentCommand;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class RejectedTaskController implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ConcurrentCommand command = (ConcurrentCommand) r;
        Socket clientSocket = command.getSocket();

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String message = "Server is shutting down."
                    + " Your request can't be served now."
                    + "Shutting down: "
                    + String.valueOf(executor.isShutdown())
                    + ". Terminated: "
                    + String.valueOf(executor.isTerminated())
                    +". Terminating: " + String.valueOf(executor.isTerminating());
            out.println(message);
            out.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
