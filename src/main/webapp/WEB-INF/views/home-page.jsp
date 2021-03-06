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
<div class="header">
<jsp:include page="fragments/header.jsp"/>
</div>
<div id="container">

    <h1>Dostępne fumkcjonalności:</h1>
    <ul>
        <li><a href="/ingredient">Ingredients</a></li>
        <li><a href="/createMeal">Create meal</a></li>
        <li><a href="/createMeal/meals">All meals</a></li>
        <li><a href="/daily-set">DailySet</a></li>
        <li><a href="/daily-set/create">CreateDailySet</a></li>
        <li><a href="/diet/createDiet">Create diet</a></li>
        <li><a href="/diet/assignDiet">Assign diet</a></li>
        <li><a href="/calendar/myCalendar">My Calendar</a></li>
        <li><a href="/calendar/dayDetails">ChooseDayDetails</a></li>
        <li><a href="/diet/diets">Diets</a></li>
        <li><a href="/userRegistration/modifyProfile">ModifyProfile</a></li>
    </ul>
</div>
<%--<div class="footer">--%>
<%--    <jsp:include page="fragments/footer.jsp"/>--%>
<%--</div>--%>
</body>
</html>

