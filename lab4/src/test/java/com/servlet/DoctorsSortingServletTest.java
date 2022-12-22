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

class DoctorsSortingServletTest {

    @Test
    void doGetTest() throws ServletException, IOException {
        DoctorsSortingServlet servlet = new DoctorsSortingServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp")).thenReturn(dispatcher);
        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    void doPostTest() throws ServletException, IOException {
        DoctorsSortingServlet servlet = new DoctorsSortingServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        AdministratorDAO dao = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp")).thenReturn(dispatcher);
        when(request.getParameter("sort")).thenReturn("category");
        servlet.doPost(request, response);

        verify(request).getParameter("sort");
        verify(servletContext).getAttribute("administratorDAO");
        verify(dao).listOfTherapists("category");
        verify(request, times(2)).setAttribute(anyString(), anyList());



    }
}