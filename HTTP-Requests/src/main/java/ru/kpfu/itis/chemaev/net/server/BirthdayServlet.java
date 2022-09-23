package ru.kpfu.itis.chemaev.net.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "birthDayServlet", urlPatterns = "/birthday")
public class BirthdayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("birthday.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String name = req.getParameter("name");
        String birthdayDate = req.getParameter("birthdayDate");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        LocalDate endDate = LocalDate.parse(formatter.format(date));
        LocalDate startDate = LocalDate.parse(birthdayDate);
        Period period = Period.between(startDate, endDate);

        if (period.isNegative()) {
            resp.sendRedirect("birthday.html");
        } else {
            String age = String.format("%d years, %d months and %d days", period.getYears(), period.getMonths(), period.getDays());

            httpSession.setAttribute("name", name);
            httpSession.setAttribute("age", age);

            resp.sendRedirect("showDate.ftl");
        }
    }
}
