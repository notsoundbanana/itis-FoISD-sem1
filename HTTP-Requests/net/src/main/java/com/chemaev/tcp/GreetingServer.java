package com.chemaev.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetingServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    public void startServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);

        String message;

        while ((message = in.readLine()) != null) {
            if ("hello".equals(message.trim().toLowerCase())) {
                out.println("Hello from server");
            } else if ("bye".equals(message.trim())) {
                out.println("Bye!");

                // stop
                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();

                break;
            } else {
                out.println("?");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GreetingServer server = new GreetingServer();
        server.startServer(5555);
    }
}
