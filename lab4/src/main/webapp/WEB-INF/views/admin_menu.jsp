<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 19/11/2022
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
    <head>
      <title>ADMIN</title>
    </head>
    <body>
    <% String login = (String)request.getSession().getAttribute("login");
       request.setAttribute("login", login);%>
        <h1>Hello ADMIN, <c:out value="${login}" />!</h1>



        <h2>Doctors</h2>
        <span>
            <form method="POST" action="/sort/doctors">
                <select name="sort" onchange="this.form.submit()">
                    <option selected>Sort By</option>
                    <option value="first_name">First name</option>
                    <option value="last_name">Last name</option>
                    <option value="category">Category</option>
                    <option value="amount_of_patients">Amount of patients</option>
                </select>
            </form>
        </span>
        <ul style="overflow: auto; height: 500px; width: 600px; border: 3px solid black">
          <c:forEach var="doctor" items="${requestScope.doctors}">
            <li>
              <p>Full name:             <c:out value="${doctor.firstName} ${doctor.lastName}"/></p>
              <p>Category:              <c:out value="${doctor.category}"/></p>
              <p>Amount of patients:    <c:out value='${doctor.amountOfPatients}'/></p>
              <p>Username:              <c:out value='${doctor.username}'/></p>
              <p>Doctor ID:              <c:out value='${doctor.id}'/></p>
            </li>
            <hr/>
          </c:forEach>
        </ul>
        <hr/>



        <h2>Patients</h2>
        <form method="POST" action="/sort/patients">
            <select name="sort" onchange="this.form.submit()">
                <option selected>Sort By</option>
                <option value="date_of_birth">Date of Birth</option>
                <option value="first_name">First name</option>
                <option value="last_name">Last name</option>
            </select>
        </form>
        <ul style="overflow: auto; height: 500px; width: 600px; border: 3px solid black">
            <c:forEach var="patient" items="${requestScope.patients}">
                <li style="display: flex; flex-direction: row; justify-content: space-between">
                    <div>
                        <p>Full name:     <c:out value="${patient.firstName} ${patient.lastName}"/></p>
                        <p>Diagnosis:     <c:out value="${patient.diagnosis}"/></p>
                        <p>Date of Birth: <c:out value='${patient.dateOfBirth}'/></p>
                        <p>Patient ID:    <c:out value='${patient.id}'/></p>
                        <p>
                            Doctor ID:
                            <c:if test="${patient.doctorId > 0}">
                                <c:out value='${patient.doctorId}'/>
                            </c:if>
                        </p>
                        <p>
                            Nurse ID:
                            <c:if test="${patient.nurseId > 0}">
                                <c:out value='${patient.nurseId}'/>
                            </c:if>
                        </p>
                        <p>
                            Discharge:
                            <c:if test="${patient.discharge eq true}">
                                <c:out value="discharged"/>
                            </c:if>
                            <c:if test="${patient.discharge eq false}">
                                <c:out value="Not discharged"/>
                            </c:if>
                        </p></div>
                    <div style="padding-top: 20px">
                        <form action="<c:url value='/assign/doctor'/>" method="GET">
                            <input hidden name="id" value="<c:out value='${patient.id}'/>"/>
                            <input class="ui-button" value="Assign doctor" type="submit"/>
                        </form>
                        <form action="<c:url value='/assign/nurse'/>" method="GET">
                            <input hidden name="id" value="<c:out value='${patient.id}'/>"/>
                            <input class="ui-button" value="Assign nurse" type="submit"/>
                        </form>
                        <form action="<c:url value='/discharge/patient'/>" method="POST">
                            <input hidden name="id" value="<c:out value='${patient.id}'/>"/>
                            <input class="ui-button" value="Discharge" type="submit"/>
                        </form>
                    </div>
                </li>
                <hr/>
            </c:forEach>
        </ul>
        <a href="<c:url value='/new/doctor' />">Register a new doctor</a><br>
        <a href="<c:url value='/new/patient' />">Register a new patient</a><br>
        <a href="<c:url value='/new/nurse' />">Register a new nurse</a><br>
        <a href="<c:url value='/logout' />">Logout</a>
    </body>
</html>
