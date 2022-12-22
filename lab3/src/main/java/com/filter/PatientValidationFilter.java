package com.filter;

import com.service.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/new/patient/create")
public class PatientValidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String firstName  = req.getParameter("firstName");
        String lastName  = req.getParameter("lastName");
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        if(!Util.firstOrLastNameIsValid(firstName)){
            writer.println("<center><h1>First name is invalid</h1></center>");
        }
        else if(!Util.firstOrLastNameIsValid(lastName)){
            writer.println("<center><h1>Last name is invalid</h1></center>");
        }
        else {
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
