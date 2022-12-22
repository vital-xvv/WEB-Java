package com.filter;

import com.DAO.AdministratorDAO;
import com.DAO.UserDAO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;
@WebFilter("/")
public class AuthentificationFilter implements Filter {

    private String login;
    private String password;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        login  = request.getParameter("login");
        password  = request.getParameter("password");

        final AtomicReference<UserDAO> userDAO = (AtomicReference<UserDAO>) request.getServletContext().getAttribute("userDAO");

        final HttpSession session = request.getSession();

        if(nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))){

            Role role = (Role)session.getAttribute("role");
            moveToMenu(request, response, role);

        }else if(userDAO.get().getUserByLoginAndPassword(login, password).getLogin() != null) {

            User user = userDAO.get().getUserByLoginAndPassword(login, password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("role", user.getRole());

            moveToMenu(request, response, user.getRole());

        }
        else {
            if(nonNull(login) && nonNull(password)) {
                response.setContentType("application/json");
                Map<String, String> error = new HashMap<>();
                error.put("status", "403");
                error.put("issuer", "Login failure");
                error.put("status_info", "FORBIDDEN");
                error.put("timestamp", LocalDateTime.now().toString());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
            else moveToMenu(request, response, Role.UNKNOWN);

        }

    }

    private void moveToMenu(final HttpServletRequest request, final HttpServletResponse response, Role role)
            throws ServletException, IOException {

        AtomicReference<AdministratorDAO> adminDAO = (AtomicReference<AdministratorDAO>) request.getServletContext().getAttribute("administratorDAO");

        if (role.equals(Role.ADMIN)) {

            request.setAttribute("doctors", adminDAO.get().listOfTherapists());
            request.setAttribute("patients", adminDAO.get().listOfPatients());

            request.getRequestDispatcher("/WEB-INF/views/admin_menu.jsp").forward(request, response);

        } else if (role.equals(Role.DOCTOR)) {
            Doctor doctor = adminDAO.get().findDoctorByUsername(login);
            List<Patient> patientList = adminDAO.get().getPatientsOfExactDoctor(doctor.getId());
            request.setAttribute("patients", patientList);
            request.getRequestDispatcher("/WEB-INF/views/doctor_menu.jsp").forward(request, response);

        }
        else if (role.equals(Role.NURSE)) {
            Nurse nurse = adminDAO.get().findNurseByUsername((String)request.getSession().getAttribute("login"));
            List<Patient> patientList = adminDAO.get().getPatientsOfExactNurse(nurse.getId());
            request.setAttribute("patients", patientList);
            request.getRequestDispatcher("/WEB-INF/views/nurse_menu.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
