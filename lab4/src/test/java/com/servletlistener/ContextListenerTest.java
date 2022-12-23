package com.servletlistener;

import com.DAO.AdministratorDAO;
import com.DAO.DoctorDAO;
import com.DAO.NurseDAO;
import com.DAO.UserDAO;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContextListenerTest {

    @Test
    void contextInitialized() {
        ContextListener contextListener = new ContextListener();
        ServletContextEvent servletContextEvent = mock(ServletContextEvent.class);
        ServletContext servletContext = mock(ServletContext.class);

        when(servletContextEvent.getServletContext()).thenReturn(servletContext);

        contextListener.contextInitialized(servletContextEvent);

        verify(servletContext, times(4)).setAttribute(anyString(), any(AtomicReference.class));

    }

    @Test
    void contextDestroyed() {

    }
}