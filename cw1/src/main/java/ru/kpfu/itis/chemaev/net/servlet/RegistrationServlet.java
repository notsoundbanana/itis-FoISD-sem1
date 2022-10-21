package ru.kpfu.itis.chemaev.net.servlet;

import ru.kpfu.itis.chemaev.net.dao.UserDao;
import ru.kpfu.itis.chemaev.net.dao.impl.UserDaoImpl;
import ru.kpfu.itis.chemaev.net.model.User;
import ru.kpfu.itis.chemaev.net.service.UserService;
import ru.kpfu.itis.chemaev.net.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auth.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String password = req.getParameter("password");

//        System.out.print(login + firstName + lastName + password);

        User existsUserWithLogin = userDao.get(login);

        if (existsUserWithLogin == null) {
            userService.save(new User(login, firstName, lastName, password));
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("error", "this login already exists");
            req.getRequestDispatcher("registration.html").forward(req, resp);
        }
    }
}

