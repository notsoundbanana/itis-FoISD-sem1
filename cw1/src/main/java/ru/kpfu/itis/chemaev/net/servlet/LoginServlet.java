package ru.kpfu.itis.chemaev.net.servlet;

import ru.kpfu.itis.chemaev.net.dao.UserDao;
import ru.kpfu.itis.chemaev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.chemaev.net.model.User;
import util.PasswordUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String encryptPassword = PasswordUtil.encrypt(password);

        User tempUser = userDao.get(login);
        if (tempUser != null) {
            if (login.equals(tempUser.getLogin()) && password.equals(encryptPassword)) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("login", login);
                httpSession.setMaxInactiveInterval(60 * 60);

                Cookie httpCookie = new Cookie("username", login);
                httpCookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(httpCookie);

                resp.sendRedirect("main.html");
            } else {
                req.setAttribute("error", "Invalid login or password");
                resp.sendRedirect("/");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
