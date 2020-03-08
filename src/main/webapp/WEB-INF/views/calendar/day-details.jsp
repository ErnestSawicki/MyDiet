<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-08
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>DayDetails</title>
</head>
<body>
<div class="date-cell">
    <p>${date.dayOfMonth}-${date.month.name()}-${date.year}</p>
</div>

<c:forEach items="${dailySetForDate.mealTime}" var="mealTime">
    <div>
        <p>Meal: ${mealTime.meal}</p>
    </div>
</c:forEach>
</body>
</html>
