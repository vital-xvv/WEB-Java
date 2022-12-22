<%--
  Created by IntelliJ IDEA.
  User: Vitalii Huzii
  Date: 12/20/2022
  Time: 4:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Make prescriptions, assign medicine or determine a diagnosis</h1>
<form action="/change/patient/prescripts" method="POST">
  <div><input name="diagnosis" type="text"  placeholder="Diagnosis: "></div>
  <div><input name="pills" type="text"  placeholder="Pills: "></div>
  <div><input name="procedures" type="text"  placeholder="Procedures: "></div>
  <div><input name="operations" type="text"  placeholder="Operations: "></div>
  <div><input type="submit" class="ui-button" value="Submit"></div>
</form>

</body>
</html>
