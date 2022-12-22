package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/assign/nurse")
public class AssignNurseToPatientServlet extends HttpServlet {
    private final AtomicInteger patientId = new AtomicInteger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");
        req.setAttribute("nurses", adminDAO.get().listOfNurses());
        final String id = req.getParameter("id");
        patientId.set(Integer.parseInt(id));
        final Patient patient = adminDAO.get().findPatientById(Integer.parseInt(id));
        req.setAttribute("patient", patient);
        req.getRequestDispatcher("/WEB-INF/views/assign_nurse.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String id = req.getParameter("id");
        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");
        adminDAO.get().assignNurseToPatient(patientId.get(),Integer.parseInt(id));
        patientId.set(0);
        resp.sendRedirect("/");
    }
}
