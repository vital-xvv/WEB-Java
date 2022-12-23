package com.filter;

import com.DAO.AdministratorDAO;
import com.DAO.UserDAO;
import com.models.*;
import org.junit.jupiter.api.Test;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthentificationFilterTest {

    @Test
    void init() {
    }

    @Test
    void doFilterTestWhenRoleUnknownFirstLogin() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        //AdministratorDAO dao2 = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        //adminDAO.set(dao2);

        when(request.getParameter("login")).thenReturn(null);
        when(request.getParameter("password")).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword(null, null)).thenReturn(
                new User(0, null, null,Role.UNKNOWN));
        when(request.getRequestDispatcher("/WEB-INF/views/login.jsp")).thenReturn(dispatcher);

        authentificationFilter.doFilter(request, response, filterChain);

        verify(dispatcher).forward(request, response);




    }

    @Test
    void doFilterTestWhenRoleUnknownWrongLoginOrPasswordLogin() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletContext servletContext = mock(ServletContext.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();


        when(request.getParameter("login")).thenReturn("vitalxvv@gmail.com");
        when(request.getParameter("password")).thenReturn("1111");
        when(request.getSession()).thenReturn(session);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword("vitalxvv@gmail.com", "1111")).thenReturn(
                new User(0, null, null,Role.UNKNOWN));

        authentificationFilter.doFilter(request, response, filterChain);

        verify(response).setContentType("application/json");
        verify(response).getOutputStream();

    }

    @Test
    void doFilterTestWhenUnauthorizedCorrectLoginAndPasswordLogin() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        ServletContext servletContext = mock(ServletContext.class);
        ServletOutputStream servletOutputStream = mock(ServletOutputStream.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        AdministratorDAO dao2 = mock(AdministratorDAO.class);
        adminDAO.set(dao2);
        List<Doctor> doctors = new ArrayList<Doctor>();
        List<Patient> patients =  new ArrayList<Patient>();

        when(request.getParameter("login")).thenReturn("vitalxvv@gmail.com");
        when(request.getParameter("password")).thenReturn("12062003");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp")).thenReturn(dispatcher);
        when(response.getOutputStream()).thenReturn(servletOutputStream);
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword("vitalxvv@gmail.com", "12062003")).thenReturn(
                new User(1, "vitalxvv@gmail.com", "12062003",Role.ADMIN));
        when(dao2.listOfTherapists()).thenReturn(doctors);
        when(dao2.listOfPatients()).thenReturn(patients);

        authentificationFilter.doFilter(request, response, filterChain);

        verify(session).setAttribute("login", "vitalxvv@gmail.com");
        verify(session).setAttribute("password", "12062003");
        verify(session).setAttribute("role", Role.ADMIN);
        verify(dao2).listOfTherapists();
        verify(dao2).listOfPatients();
        verify(request).setAttribute("doctors", doctors);
        verify(request).setAttribute("patients", patients);


        verify(dispatcher).forward(request, response);





    }


    @Test
    void doFilterTestWhenRoleAdminAuthenticated() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        AdministratorDAO dao2 = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao2);

        when(request.getParameter("login")).thenReturn("vitalxvv@gmail.com");
        when(request.getParameter("password")).thenReturn("12062003");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.ADMIN);
        when(session.getAttribute("login")).thenReturn("vitalxvv@gmail.com");
        when(session.getAttribute("password")).thenReturn("12062003");
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword("vitalxvv@gmail.com", "12062003")).thenReturn(
                new User(1, "vitalxvv@gmail.com", "12062003",Role.ADMIN));
        when(request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp")).thenReturn(dispatcher);

        authentificationFilter.doFilter(request, response, filterChain);

        verify(dao2).listOfTherapists();
        verify(dao2).listOfPatients();
        verify(request, times(2)).setAttribute(anyString(), anyList());
        verify(dispatcher).forward(request, response);

    }

    @Test
    void doFilterTestWhenRoleDoctorAuthenticated() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        AdministratorDAO dao2 = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao2);

        when(request.getParameter("login")).thenReturn("vitalxvv@gmail.com");
        when(request.getParameter("password")).thenReturn("12062003");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.DOCTOR);
        when(session.getAttribute("login")).thenReturn("vitalxvv@gmail.com");
        when(session.getAttribute("password")).thenReturn("12062003");
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword("vitalxvv@gmail.com", "12062003")).thenReturn(
                new User(1, "vitalxvv@gmail.com", "12062003",Role.DOCTOR));
        when(request.getRequestDispatcher("/WEB-INF/views/doctor_menu.jsp")).thenReturn(dispatcher);
        when(dao2.findDoctorByUsername("vitalxvv@gmail.com")).thenReturn(
                new Doctor(1, "Vitalii", "Huzii", "oncologist"));
        when(dao2.getPatientsOfExactDoctor(1)).thenReturn(new ArrayList<Patient>());
        authentificationFilter.doFilter(request, response, filterChain);

        verify(dao2).findDoctorByUsername("vitalxvv@gmail.com");
        verify(dao2).getPatientsOfExactDoctor(1);
        verify(dispatcher).forward(request, response);

    }

    @Test
    void doFilterTestWhenRoleNurseAuthenticated() throws ServletException, IOException {
        AuthentificationFilter authentificationFilter = new AuthentificationFilter();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        UserDAO dao = mock(UserDAO.class);
        AtomicReference<UserDAO> userDAO = new AtomicReference<>();
        userDAO.set(dao);
        AdministratorDAO dao2 = mock(AdministratorDAO.class);
        AtomicReference<AdministratorDAO> adminDAO = new AtomicReference<>();
        adminDAO.set(dao2);

        when(request.getParameter("login")).thenReturn("vitalxvv@gmail.com");
        when(request.getParameter("password")).thenReturn("12062003");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("role")).thenReturn(Role.NURSE);
        when(session.getAttribute("login")).thenReturn("vitalxvv@gmail.com");
        when(session.getAttribute("password")).thenReturn("12062003");
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute("userDAO")).thenReturn(userDAO);
        when(servletContext.getAttribute("administratorDAO")).thenReturn(adminDAO);
        when(dao.getUserByLoginAndPassword("vitalxvv@gmail.com", "12062003")).thenReturn(
                new User(1, "vitalxvv@gmail.com", "12062003",Role.NURSE));
        when(request.getRequestDispatcher("/WEB-INF/views/nurse_menu.jsp")).thenReturn(dispatcher);
        when(dao2.findNurseByUsername("vitalxvv@gmail.com")).thenReturn(new Nurse(1, "Vitalii", "Huzii","public health nursing", "username"));
        when(dao2.getPatientsOfExactNurse(1)).thenReturn(new ArrayList<Patient>());
        authentificationFilter.doFilter(request, response, filterChain);

        verify(dao2).findNurseByUsername("vitalxvv@gmail.com");
        verify(dao2).getPatientsOfExactNurse(1);
        verify(dispatcher).forward(request, response);

    }


    @Test
    void destroy() {

    }
}