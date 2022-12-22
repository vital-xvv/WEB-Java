package com.servlet;

import com.DAO.AdministratorDAO;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DischargePatientServletTest {

    @Test
    void doPostTest() throws ServletException, IOException {
        DischargePatientServlet dischargePatientServlet = new DischargePatientServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getParameter("id")).thenReturn("2");
        when(request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp")).thenReturn(dispatcher);

        dischargePatientServlet.doPost(request, response);

        verify(request).getParameter("id");
        verify(dao).setDischarge(2, true);
        verify(dispatcher).forward(request, response);

    }
}