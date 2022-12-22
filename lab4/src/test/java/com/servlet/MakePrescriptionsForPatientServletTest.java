package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Doctor;
import com.models.Nurse;
import com.models.Patient;
import com.models.Role;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class MakePrescriptionsForPatientServletTest {

    @Test
    void doGetTestIfDoctor() throws ServletException, IOException {
        MakePrescriptionsForPatientServlet servlet = new MakePrescriptionsForPatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getRequestDispatcher("/WEB-INF/views/doctor_menu.jsp")).thenReturn(dispatcher);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("osborne.j@gmail.com");
        when(session.getAttribute("role")).thenReturn(Role.DOCTOR);
        when(dao.findDoctorByUsername("osborne.j@gmail.com")).thenReturn(new Doctor("Davis", "Smith", "psychologist", "username"));

        servlet.doGet(request, response);

        verify(dao).findDoctorByUsername("osborne.j@gmail.com");
        verify(dao).getPatientsOfExactDoctor(anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doGetTestIfNurse() throws ServletException, IOException {
        MakePrescriptionsForPatientServlet servlet = new MakePrescriptionsForPatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(request.getRequestDispatcher("/WEB-INF/views/nurse_menu.jsp")).thenReturn(dispatcher);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("dav");
        when(session.getAttribute("role")).thenReturn(Role.NURSE);
        when(dao.findNurseByUsername("dav")).thenReturn(new Nurse("Davis", "Smith", "public health nursing", "username"));

        servlet.doGet(request, response);

        verify(dao).findNurseByUsername("dav");
        verify(dao).getPatientsOfExactNurse(anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doPostTest() throws ServletException, IOException {
        MakePrescriptionsForPatientServlet servlet = new MakePrescriptionsForPatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        HttpSession session = mock(HttpSession.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(session.getAttribute("role")).thenReturn(Role.NURSE);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("login")).thenReturn("username");
        when(request.getRequestDispatcher("/WEB-INF/views/nurse_menu.jsp")).thenReturn(dispatcher);
        when(request.getParameter("id")).thenReturn("2");
        when(dao.findNurseByUsername("username")).thenReturn(new Nurse("Davis", "Smith",
                "public health nursing", "username"));
        when(request.getParameter("diagnosis")).thenReturn("Cancer");
        when(request.getParameter("pills")).thenReturn("Gardlium Forte");
        when(request.getParameter("procedures")).thenReturn("Chemical procedure");
        when(request.getParameter("operations")).thenReturn("Operation");

        servlet.doPost(request, response);

        verify(request).getParameter("id");
        verify(request).getParameter("diagnosis");
        verify(request).getParameter("pills");
        verify(request).getParameter("procedures");
        verify(request).getParameter("operations");
        verify(dao).updatePrescriptions(2, "Cancer",
                "Gardlium Forte", "Chemical procedure", "Operation");
        verify(dispatcher).forward(request, response);







    }
}