package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/new/doctor/create")
public class RegisterDoctorServletWithValidationFilter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName  = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String category  = request.getParameter("category");
        String username  = request.getParameter("username");

        Doctor doctor = new Doctor(firstName, lastName, category, username);

        final AtomicReference<AdministratorDAO> adminDAO =
                (AtomicReference<AdministratorDAO>) request.getServletContext().
                        getAttribute("administratorDAO");

        adminDAO.get().addDoctor(doctor);

        response.sendRedirect("/");
    }
}
