<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-08
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/calendar/dayDetails">
    <input type="date" name="date"/>
    <button type="submit">ChooseDate</button>
    <sec:csrfInput/>
</form>
</body>
</html>
