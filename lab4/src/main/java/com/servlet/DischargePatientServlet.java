package com.servlet;

import com.DAO.AdministratorDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/discharge/patient")
public class DischargePatientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");
        int patientId = Integer.parseInt(req.getParameter("id"));
        adminDAO.get().setDischarge(patientId, true);
        resp.sendRedirect("/");
    }
}
