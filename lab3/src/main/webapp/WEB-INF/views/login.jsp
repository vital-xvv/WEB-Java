<%--
  Created by IntelliJ IDEA.
  User: vital
  Date: 19/11/2022
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN</title>
</head>
<body>
    <h1>Please, log in</h1>
    <form action="" method="POST">
        <div><input name="login" type="text" required placeholder="Username: "></div>
        <div><input name="password" type="password" required placeholder="Password: "></div>
        <div><input type="submit" class="ui-button" value="Submit"></div>
    </form>

</body>
</html>
