package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private final Logger logs = Logger.getLogger(LogoutServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        logs.warning("\nUser " + session.getAttribute("login") + " has been logged out");
        session.removeAttribute("login");
        session.removeAttribute("password");
        session.removeAttribute("role");

        response.sendRedirect("/");


    }
}
