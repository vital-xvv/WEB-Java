<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 20/11/2022
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register new Nurse</title>
</head>
<body>
<h1>Register a new nurse</h1>
<form action="/new/nurse/create" method="POST">
    <div><input name="firstName" type="text" required placeholder="First name: "></div>
    <div><input name="lastName" type="text" required placeholder="Last Name: "></div>
    <div><input name="specialization" type="text" required placeholder="Specialization: "></div>
    <div><input name="username" type="text" required placeholder="System username: "></div>
    <div><input type="submit" class="ui-button" value="Submit"></div>
</form>

</body>
</html>