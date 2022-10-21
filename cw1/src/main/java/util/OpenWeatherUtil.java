package util;

import ru.kpfu.itis.chemaev.net.client.Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class OpenWeatherUtil {

    public static Object getTemperature(String city) throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(OpenWeatherUtil.class.getResourceAsStream("/openWeather.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = "https://api.openweathermap.org/data/2.5/weather";

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Map<String, String> params = new HashMap<>();
        params.put("q", city);
        params.put("appid", properties.getProperty("apiKey"));

        Client client = new Client();
        System.out.println(client.get(url, headers, params));

        return null;
    }
}
