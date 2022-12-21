package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Doctor;
import com.models.Nurse;
import com.models.Patient;
import com.models.Role;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/change/patient/prescripts")
public class MakePrescriptionsForPatientServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("role").equals(Role.DOCTOR)) {
            Doctor doctor = ((AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO")).get().findDoctorByUsername((String)req.getSession().getAttribute("login"));
            List<Patient> patientList = ((AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO")).get().getPatientsOfExactDoctor(doctor.getId());
            req.setAttribute("patients", patientList);
            req.getRequestDispatcher("/WEB-INF/views/doctor_menu.jsp").forward(req, resp);}
        if(req.getSession().getAttribute("role").equals(Role.NURSE)){
            Nurse nurse = ((AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO")).get().findNurseByUsername((String)req.getSession().getAttribute("login"));
            List<Patient> patientList = ((AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO")).get().getPatientsOfExactNurse(nurse.getId());
            req.setAttribute("patients", patientList);
            req.getRequestDispatcher("/WEB-INF/views/nurse_menu.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        String diagnosis  = req.getParameter("diagnosis");
        String pills  = req.getParameter("pills");
        String procedures  = req.getParameter("procedures");
        String operations  = req.getParameter("operations");
        final AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) req.getServletContext().getAttribute("administratorDAO");
        adminDAO.get().updatePrescriptions(id, diagnosis, pills, procedures, operations);
        doGet(req, resp);
    }
}
