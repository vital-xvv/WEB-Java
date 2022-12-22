package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/new/patient/create")
public class RegisterPatientServletWithValidationFilter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName  = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String dateOfBirth  = req.getParameter("dateOfBirth");

        Patient patient = new Patient(firstName, lastName, dateOfBirth);

        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");

        adminDAO.get().addPatient(patient);

        resp.sendRedirect("/");
    }
}
