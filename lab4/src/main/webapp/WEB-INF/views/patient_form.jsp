<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 20/11/2022
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Register a new patient</h1>
<form action="/new/patient/create" method="POST">
    <div><input name="firstName" type="text" required placeholder="First name: "></div>
    <div><input name="lastName" type="text" required placeholder="Last Name: "></div>
    <div><input name="dateOfBirth" type="date" required placeholder="Date of birth: "></div>
    <div><input type="submit" class="ui-button" value="Submit"></div>
</form>
</body>
</html>
