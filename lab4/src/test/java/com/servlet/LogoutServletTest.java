package com.servlet;

import org.junit.jupiter.api.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

class LogoutServletTest {

    @Test
    void doGetTest() throws IOException, ServletException {
        LogoutServlet logoutServlet = new LogoutServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        logoutServlet.doGet(request,response);

        verify(session).removeAttribute("login");
        verify(session).removeAttribute("password");
        verify(session).removeAttribute("role");

        verify(response).sendRedirect("/");



    }
}