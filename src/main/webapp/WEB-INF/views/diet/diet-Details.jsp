<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-29
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>DietDetails</title>
</head>
<body>
<div>
    <div>
        <label>Diet name: </label>
    </div>
    <div>
        <input type="text" value="${dietDetails.dietName}">
    </div>
</div>
<div>
    <div>
        <label>Duration: </label>
    </div>
    <div>
        <input type="text" value="${dietDetails.duration}">
    </div>
</div>
<div>
    <div>
        <label>DailySets</label>
    </div>
    <div>
        <c:forEach items="${dietDetails.dailySets}" var="dailySet">
            ${dailySet.mealForDailyDTO}
        </c:forEach>
    </div>
</div>


</body>
</html>
