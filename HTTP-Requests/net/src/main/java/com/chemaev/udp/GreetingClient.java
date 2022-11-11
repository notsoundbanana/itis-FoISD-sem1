package com.chemaev.udp;


import java.io.IOException;
import java.net.*;

import static com.chemaev.udp.GreetingServer.PORT;

public class GreetingClient {
    private DatagramSocket socket;
    private byte[] buffer;
    private final InetAddress address;

    public GreetingClient() throws SocketException, UnknownHostException {
        this.socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String send(String message) throws IOException {
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
        socket.send(packet);

        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String response = new String(packet.getData(), 0, packet.getLength());

        return response;
    }

    public void stopClient() {
        socket.close();
    }
}
