<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 23/11/2022
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2> Add a doctor to the patient: <c:out value="${requestScope.patient.firstName} ${requestScope.patient.lastName}"/> </h2>
<h1>List of doctors:</h1>
<ul style="overflow: auto; height: 500px; width: 600px">
    <c:forEach var="doctor" items="${requestScope.doctors}">
        <li>
            <div>
                <p><c:out value="${doctor.firstName} ${doctor.lastName}"/></p>
                <p><c:out value="${doctor.category}"/></p>
                <p><c:out value='${doctor.amountOfPatients}'>No amount</c:out></p>
            </div>
            <span>
                <form action="<c:url value='/assign/doctor'/>" method="POST">
                    <input hidden value="<c:out value='${doctor.id}'/>" type="number" name="id"/>
                    <input class="ui-button" value="Select" type="submit"/>
                </form>
            </span>


        </li>
        <hr/>
    </c:forEach>
</ul>


</body>
</html>
