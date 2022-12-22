package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Doctor;
import com.models.Patient;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class RegisterDoctorServletWithValidationFilterTest {

    @Test
    void doPostTest() throws ServletException, IOException {
        RegisterDoctorServletWithValidationFilter doctorServlet = new RegisterDoctorServletWithValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        AdministratorDAO dao = mock(AdministratorDAO.class);
        ServletContext context = mock(ServletContext.class);

        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getParameter("firstName")).thenReturn("Margo");
        when(request.getParameter("lastName")).thenReturn("Stew");
        when(request.getParameter("category")).thenReturn("psychologist");
        when(request.getParameter("username")).thenReturn("username");

       doctorServlet.doPost(request, response);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(dao).addDoctor(any(Doctor.class));

        verify(response).sendRedirect("/");
    }
}