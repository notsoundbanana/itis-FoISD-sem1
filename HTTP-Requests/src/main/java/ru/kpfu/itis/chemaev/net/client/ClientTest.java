package ru.kpfu.itis.chemaev.net.client;

import java.util.HashMap;
import java.util.Map;

public class ClientTest {
    public static void main(String[] args) {
        Map<String, String> getParams = new HashMap<>();
        Map<String, String> getHeaders = new HashMap<>();

        getHeaders.put("Content-Type", "application/json");

        Client client = new Client();
        System.out.println(client.get("https://postman-echo.com/get", getHeaders, getParams));

        Map<String, String> postParams = new HashMap<>();
        Map<String, String> postHeaders = new HashMap<>();

        postHeaders.put("Content-Type", "application/json");
        postParams.put("name", "Daniil_Chemaev");
        postParams.put("data", "123");

        System.out.println(client.post("https://postman-echo.com/post/", postHeaders, postParams));

    }
}
