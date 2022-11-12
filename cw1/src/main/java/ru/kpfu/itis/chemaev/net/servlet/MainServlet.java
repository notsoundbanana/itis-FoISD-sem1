package ru.kpfu.itis.chemaev.net.servlet;

import org.json.JSONObject;
import ru.kpfu.itis.chemaev.net.model.Weather;
import ru.kpfu.itis.chemaev.net.util.OpenWeatherUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainServlet", urlPatterns = "/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }

    // !TODO 400 на пустом городе
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String city = req.getParameter("city");
        req.setAttribute("city", city);
        System.out.println(city);
        String weatherJSON = (String) OpenWeatherUtil.getWeather(city);

        JSONObject obj = new JSONObject(weatherJSON);
        Double temperature = obj.getJSONObject("main").getDouble("temp");
        Weather weather = new Weather(city, String.format("Value: %.2f", temperature - 273.15));

        System.out.println(weatherJSON);

        System.out.println("TEMPERATURE " + weather.temperature);
        resp.getWriter().write(weather.temperature);
    }
}
