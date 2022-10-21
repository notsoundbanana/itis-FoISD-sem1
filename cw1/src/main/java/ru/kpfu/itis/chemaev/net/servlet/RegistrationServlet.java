package ru.kpfu.itis.chemaev.net.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password123";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("register.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String _username = req.getParameter("_username");
        String _email = req.getParameter("_email");
        String _password = req.getParameter("_password");
        String _conpassword = req.getParameter("_conpassword");
        String _remember_me = req.getParameter("_remember_me");

        System.out.println(_username);
        System.out.println(_email);
        System.out.println(_password);
        System.out.println(_conpassword);
        System.out.println(_remember_me);

//        String salt = PasswordUtil.generateSalt(512).get().toString();
//        String key = PasswordUtil.hashPassword(_password, salt).get().toString();

//        if(_password.equals(_conpassword)){
            // добавление в бд юзера
            // пересылка на страницу с заполнением персональных данных для выборп плана тренировок
            // какого вы пола?
            // что  хотите прорабоать (руки, грудь, пресс, ног, все тело)
            // какая у вас цель (сбросить вес, нарастить массу, быть в форме)
            // какая у вас сейчас форму (стройная, средняя, пышная)
            // как вас зовут ?
            // сколько вам лет ?
            // укажите свой рост
            // укажите свой вес ?
            // как часто хотите тренироваться ?
        }







//        if (LOGIN.equals(login) && PASSWORD.equals(password)) {
//            logger.info("User with username = {} logged in", login);
//            HttpSession httpSession = req.getSession();
//            httpSession.setAttribute("username", login);
//            httpSession.setMaxInactiveInterval(60 * 60);
//
//            Cookie httpCookie = new Cookie("username", login);
//            httpCookie.setMaxAge(24 * 60 * 60);
//            resp.addCookie(httpCookie);
//
//            resp.sendRedirect("main.jsp");
//        } else {
//            resp.sendRedirect("/login");
//        }
//    }
}

