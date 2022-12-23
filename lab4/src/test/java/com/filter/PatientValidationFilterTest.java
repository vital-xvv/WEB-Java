package com.filter;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class PatientValidationFilterTest {

    @Test
    void init() {
    }

    @Test
    void doFilterTestIfFormDataIsValid() throws IOException, ServletException {
        PatientValidationFilter patientValidationFilter = new PatientValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        patientValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");

        verify(response).setContentType("application/json");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterTestIfFirstNameIsInvalid() throws ServletException, IOException {
        PatientValidationFilter patientValidationFilter = new PatientValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("c9aren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        patientValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfLastNameIsInvalid() throws ServletException, IOException {
        PatientValidationFilter patientValidationFilter = new PatientValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox2");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        patientValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void destroy() {
    }
}