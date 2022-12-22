package com.servlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class RegisterNewDoctorServletTest {

    @Test
    public void whenCallDoGetThenServletReturnDoctorFormPage() throws ServletException, IOException {
        RegisterNewDoctorServlet registerNewDoctorServlet = new RegisterNewDoctorServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/WEB-INF/views/doctor_form.jsp")).thenReturn(dispatcher);
        registerNewDoctorServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/WEB-INF/views/doctor_form.jsp");
        //verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
}