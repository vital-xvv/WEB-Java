package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/sort/doctors")
public class DoctorsSortingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sort  = req.getParameter("sort");
        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");
        List<Doctor> doctors = adminDAO.get().listOfTherapists(sort);
        req.setAttribute("patients", adminDAO.get().listOfPatients());
        req.setAttribute("doctors", doctors);
        doGet(req,resp);


    }
}
