package com.chemaev.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ClientThread implements Runnable {
    private final BufferedReader input;
    private final BufferedWriter output;
    private ChatClient chatClient;

    public BufferedReader getInput() {
        return input;
    }

    public BufferedWriter getOutput() {
        return output;
    }


    public ClientThread(BufferedReader input, BufferedWriter output, ChatClient chatClient) {
        this.input = input;
        this.output = output;
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.readLine();
                chatClient.getChatApplication().appendMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
