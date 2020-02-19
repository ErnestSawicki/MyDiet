<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 15.02.2020
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div id="container">

    Dostępne fumkcjonalności:
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/ingredient">Utwórz składnik</a></li>
        <li><a href="/createMeal">Utwórz danie</a></li>
    </ul>




</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

