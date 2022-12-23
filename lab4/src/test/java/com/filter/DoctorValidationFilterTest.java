package com.filter;

import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import static org.mockito.Mockito.*;

class DoctorValidationFilterTest {


    @Test
    void initTest() {

    }

    @Test
    void doFilterTestIfFormDataIsValid() throws ServletException, IOException {
        DoctorValidationFilter doctorValidationFilter = new DoctorValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("category")).thenReturn("psychologist");
        when(request.getParameter("username")).thenReturn("username");

        doctorValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");
        verify(filterChain).doFilter(request, response);
    }

    @Test
    void doFilterTestIfFirstNameIsInvalid() throws ServletException, IOException {
        DoctorValidationFilter doctorValidationFilter = new DoctorValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("c9aren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("category")).thenReturn("psychologist");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        doctorValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfLastNameIsInvalid() throws ServletException, IOException {
        DoctorValidationFilter doctorValidationFilter = new DoctorValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("fox1_");
        when(request.getParameter("category")).thenReturn("psychologist");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        doctorValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfCategoryIsInvalid() throws ServletException, IOException {
        DoctorValidationFilter doctorValidationFilter = new DoctorValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("category")).thenReturn("psychologis");
        when(request.getParameter("username")).thenReturn("username");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        doctorValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void doFilterTestIfUsernameIsInvalid() throws ServletException, IOException {
        DoctorValidationFilter doctorValidationFilter = new DoctorValidationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);

        when(request.getParameter("firstName")).thenReturn("Caren");
        when(request.getParameter("lastName")).thenReturn("Fox");
        when(request.getParameter("category")).thenReturn("psychologist");
        when(request.getParameter("username")).thenReturn("2username$%%%");
        when(response.getOutputStream()).thenReturn(servletOutputStream);

        doctorValidationFilter.doFilter(request, response, filterChain);

        verify(request).getParameter("firstName");
        verify(request).getParameter("lastName");
        verify(request).getParameter("category");
        verify(request).getParameter("username");

        verify(response).setContentType("application/json");

        verify(response).setStatus(406);
        verify(response).getOutputStream();
    }

    @Test
    void destroy() {

    }

}