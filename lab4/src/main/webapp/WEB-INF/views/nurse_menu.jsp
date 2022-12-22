<%--
  Created by IntelliJ IDEA.
  User: Vitalii Huzii
  Date: 12/20/2022
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title> <c:out value="${login}" /></title>
</head>
<body>
<% String login = (String)request.getSession().getAttribute("login");
    request.setAttribute("login", login);%>


<h1>Hello  Nurse, <c:out value="${login}" />!</h1>


<ul style="overflow: auto; height: 500px; width: 600px; border: 3px solid black">
    <c:forEach var="patient" items="${requestScope.patients}">
        <li>
            <p><c:out value="${patient.firstName} ${patient.lastName}"/></p>
            <p><c:out value="${patient.diagnosis}"/></p>
            <p><c:out value='${patient.dateOfBirth}'/></p>
            <p><c:out value='Id: ${patient.id}'/></p>
            <span><form action="<c:url value='/change/patient/prescripts'/>" method="POST">
                <div><input name="pills" type="text"  placeholder="Pills: " value="<c:out value='${patient.pills}'/>"></div>
                <div><input name="procedures" type="text"  placeholder="Procedures: " value="<c:out value='${patient.procedures}'/>"></div>
                <input hidden name="id" value="<c:out value='${patient.id}'/>"/>
                <input class="ui-button" value="Make prescriptions" type="submit"/>
            </form></span>
        </li>
        <hr/>
    </c:forEach>
</ul>


<a href="<c:url value='/logout' />">Logout</a>


</body>
</html>
