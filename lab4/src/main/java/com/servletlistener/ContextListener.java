package com.servletlistener;

import com.DAO.AdministratorDAO;
import com.DAO.DoctorDAO;
import com.DAO.NurseDAO;
import com.DAO.UserDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.atomic.AtomicReference;

@WebListener
public class ContextListener implements ServletContextListener {

    private AtomicReference<UserDAO> userDAO;
    private AtomicReference<AdministratorDAO> administratorDAO;
    private AtomicReference<NurseDAO> nurseDAO;
    private AtomicReference<DoctorDAO> doctorDAO;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

            userDAO = new AtomicReference<>(new UserDAO());
            nurseDAO = new AtomicReference<>(new NurseDAO());
            doctorDAO = new AtomicReference<>(new DoctorDAO());
            administratorDAO = new AtomicReference<>(new AdministratorDAO());

            final ServletContext servletContext = servletContextEvent.getServletContext();

            servletContext.setAttribute("userDAO", userDAO);
            servletContext.setAttribute("doctorDAO", doctorDAO);
            servletContext.setAttribute("nurseDAO", nurseDAO);
            servletContext.setAttribute("administratorDAO", administratorDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        userDAO = null;
        nurseDAO = null;
        doctorDAO = null;
        administratorDAO = null;

    }
}
