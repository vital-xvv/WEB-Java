package com.servlet;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.mockito.Mockito.*;

class RegisterNewPatientServletTest {

    @Test
    void whenCallDoGetThenServletReturnPatientFormPage() throws ServletException, IOException {
        RegisterNewPatientServlet registerNewPatientServlet = new RegisterNewPatientServlet();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/WEB-INF/views/patient_form.jsp")).thenReturn(dispatcher);
        registerNewPatientServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("/WEB-INF/views/patient_form.jsp");
        verify(dispatcher).forward(request, response);
    }
}