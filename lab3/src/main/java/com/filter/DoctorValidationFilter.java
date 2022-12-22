package com.filter;

import com.service.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


@WebFilter("/new/doctor/create")
public class DoctorValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String firstName  = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        String category  = req.getParameter("category");
        String username  = req.getParameter("username");
        resp.setContentType("application/json");
        Map<String, String> error = new HashMap<>();
        error.put("status", "406");
        error.put("issuer", "Doctor form info Validation");
        error.put("status_info", "NOT_ACCEPTABLE");
        if(!Util.firstOrLastNameIsValid(firstName)){
            resp.setStatus(406);
            error.put("error_message", "firstName is invalid");
            error.put("timestamp", LocalDateTime.now().toString());
            new ObjectMapper().writeValue(resp.getOutputStream(), error);
        }
        else if(!Util.firstOrLastNameIsValid(lastName)){
            resp.setStatus(406);
            error.put("error_message", "lastName is invalid");
            error.put("timestamp", LocalDateTime.now().toString());
            new ObjectMapper().writeValue(resp.getOutputStream(), error);
        }
        else if(!Util.categoryIsValid(category)){
            resp.setStatus(406);
            error.put("error_message", "category is invalid");
            error.put("timestamp", LocalDateTime.now().toString());
            new ObjectMapper().writeValue(resp.getOutputStream(), error);
        }
        else if(!Util.usernameIsValid(username) && !Util.emailIsValid(username)){
            resp.setStatus(406);
            error.put("error_message", "username is invalid");
            error.put("timestamp", LocalDateTime.now().toString());
            new ObjectMapper().writeValue(resp.getOutputStream(), error);
        }
        else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
