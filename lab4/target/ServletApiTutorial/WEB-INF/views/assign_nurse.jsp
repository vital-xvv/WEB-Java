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

<h2> Assign a nurse to the patient: <c:out value="${requestScope.patient.firstName} ${requestScope.patient.lastName}"/> </h2>
<h3>List of nurses:</h3>
<ul style="overflow: auto; height: 500px; width: 600px">
    <c:forEach var="nurse" items="${requestScope.nurses}">
        <li>
            <div>
                <p><c:out value="${nurse.firstName} ${nurse.lastName}"/></p>
                <p>Specialization: <c:out value="${nurse.specialization}"/></p>
                <p>Nurse ID: <c:out value="${nurse.id}"/></p>
                <p>Username: <c:out value="${nurse.username}"/></p>
            </div>
            <span>
                <form action="<c:url value='/assign/nurse'/>" method="POST">
                    <input hidden value="<c:out value='${nurse.id}'/>" type="number" name="id"/>
                    <input class="ui-button" value="Select" type="submit"/>
                </form>
            </span>


        </li>
        <hr/>
    </c:forEach>
</ul>


</body>
</html>