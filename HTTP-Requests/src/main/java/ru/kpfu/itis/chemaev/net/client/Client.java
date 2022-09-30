package ru.kpfu.itis.chemaev.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/*
args	{}
headers
x-forwarded-proto	"https"
x-forwarded-port	"443"
host	"postman-echo.com"
x-amzn-trace-id	"Root=1-6330581f-1861f4ee79674dfa169fb69d"
user-agent	"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:104.0) Gecko/20100101 Firefox/104.0"
accept	"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*;q=0.8"
        accept-language	"en-US,en;q=0.5"
        accept-encoding	"gzip, deflate, br"
        upgrade-insecure-requests	"1"
        sec-fetch-dest	"document"
        sec-fetch-mode	"navigate"
        sec-fetch-site	"none"
        sec-fetch-user	"?1"
        dnt	"1"
        sec-gpc	"1"
        if-none-match	"W/\"274-1buPc6coAq9VGRvePrbQCChxxVA\""
        cookie	"sails.sid=s%3AbeIdA9CPWaC0qwfnlauAcQdrj92XNTAy.wt8U3fLvsTCttZwxbg28J3IIS%2F%2F6qa0WlQmg8ELKTok"
        url	"https://postman-echo.com/get"
* */

public class Client implements HttpClient {

    private String formatParams(Map<String, String> params) {  // Форматирование параметров под URL запрос
        StringBuilder res = new StringBuilder("?");
        params.forEach((key, value) -> res.append(String.format("%s=%s&", key, value)));

        return String.valueOf(res.deleteCharAt(res.length() - 1));
    }

    private HttpURLConnection setConnection(String requestType, URL url, Map<String, String> headers) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestType);
        headers.forEach((key, value) -> connection.setRequestProperty(key, value));
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        return connection;
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        StringBuilder content = new StringBuilder();
        String input;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            while ((input = reader.readLine()) != null) {
                content.append(input);
            }
        }
        return String.valueOf(content);
    }

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        String res;
        try {
            URL urlWithParams = new URL(url + formatParams(params));

            HttpURLConnection connection = setConnection("GET", urlWithParams, headers);

            System.out.println(connection.getResponseCode());

            res = getResponse(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        String res;
        try {
            URL urlWithParams = new URL(url + formatParams(params));

            HttpURLConnection connection = setConnection("POST", urlWithParams, headers);

            connection.setDoOutput(true);

            System.out.println(connection.getResponseCode());

            res = getResponse(connection);

            connection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}