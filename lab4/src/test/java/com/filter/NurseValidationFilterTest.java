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

class NurseValidationFilterTest {

    @Test
    void init() {
    }

    @Test
    void doFilterTestIfFormDataIsValid() throws ServletException, IOException {
        NurseValidationFilter nurseValidationFilter = new NurseValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("specialization")).thenReturn("public heath nursing");
        when(request.getParameter("username")).thenReturn("username");

        nurseValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("specialization");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterTestIfFirstNameIsInvalid() throws ServletException, IOException {
        NurseValidationFilter nurseValidationFilter = new NurseValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("c9aren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("specialization")).thenReturn("public heath nursing");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        nurseValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("specialization");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfLastNameIsInvalid() throws ServletException, IOException {
        NurseValidationFilter nurseValidationFilter = new NurseValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox2");
        when(request.getParameter("specialization")).thenReturn("public heath nursing");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        nurseValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("specialization");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfSpecializationIsInvalid() throws ServletException, IOException {
        NurseValidationFilter nurseValidationFilter = new NurseValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("specialization")).thenReturn("publicity heath nursing");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        nurseValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("specialization");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfUSernameIsInvalid() throws ServletException, IOException {
        NurseValidationFilter nurseValidationFilter = new NurseValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("specialization")).thenReturn("public heath nursing");
        when(request.getParameter("username")).thenReturn("1username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        nurseValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("specialization");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void destroy() {
    }
}