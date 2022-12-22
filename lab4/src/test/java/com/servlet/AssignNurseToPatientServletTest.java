package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Patient;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class AssignNurseToPatientServletTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        AssignNurseToPatientServlet assignNurseToPatientServlet = new AssignNurseToPatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);
        Patient patient = mock(Patient.class);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getRequestDispatcher("/WEB-INF/views/assign_nurse.jsp")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("2");
        when(dao.findPatientById(2)).thenReturn(patient);

        assignNurseToPatientServlet.doGet(request, response);

        verify(request).setAttribute(anyString(), anyList());
        verify(request).getParameter("id");
        verify(dao).findPatientById(2);
        verify(request).setAttribute("patient", patient );
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doPostTest() throws IOException, ServletException {
        AssignNurseToPatientServlet assignNurseToPatientServlet = new AssignNurseToPatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletContext servletContext = mock(ServletContext.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getParameter("id")).thenReturn("2");

        assignNurseToPatientServlet.doPost(request, response);

        verify(request).getParameter("id");
        verify(dao).assignNurseToPatient(0,2);
        verify(response).sendRedirect("/");
    }
}