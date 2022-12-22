package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Nurse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/new/nurse/create")
public class RegisterNurseServletWithValidationFilter extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName  = request.getParameter("firstName");
        String lastName  = request.getParameter("lastName");
        String specialization  = request.getParameter("specialization");
        String username  = request.getParameter("username");

        Nurse nurse = new Nurse(firstName, lastName, specialization, username);

        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) request.getServletContext().getAttribute("administratorDAO");

        adminDAO.get().addAndRegisterNurse(nurse);

        response.sendRedirect("/");
    }
}
