package com.servlet;

import com.DAO.AdministratorDAO;
import com.models.Patient;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Mockito.*;

class RegisterPatientServletWithValidationFilterTest {

    @Test
    void doPostTest() throws IOException, ServletException {
        RegisterPatientServletWithValidationFilter patientServlet = new RegisterPatientServletWithValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        AdministratorDAO dao = mock(AdministratorDAO.class);
        ServletContext context = mock(ServletContext.class);

        adminDAO.set(dao);

        when(request.getServletContext()).thenReturn(context);
        when(context.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(request.getParameter("firstName")).thenReturn("Margo");
        when(request.getParameter("lastName")).thenReturn("Stone");
        when(request.getParameter("dateOfBirth")).thenReturn("1999-06-12");

        patientServlet.doPost(request, response);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("dateOfBirth");

        verify(dao).addPatient(any(Patient.class));

        verify(response).sendRedirect("/");

    }
}