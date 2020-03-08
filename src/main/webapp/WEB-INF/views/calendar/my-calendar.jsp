<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-08
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        .day-of-week,
        .date-grid {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
        }

        .date-grid button:first-child {
            grid-column: ${calendarProperties.firstDayOfMonth};
        }
    </style>
</head>
<body>
<form method="get" action="/calendar/myCalendar/monthChange">
<div class="month">
    <div>
        <button type="submit" value="prevMonth" name="monthChange">prev</button>
    </div>
    <div>
        <p>March</p>
    </div>
    <div>
        <input type="hidden" name="viewMonth" value="${viewMonth}"/>
        <input type="hidden" name="viewYear" value="${viewYear}"/>

        <button type="submit" value="nextMonth" name="monthChange">next</button>
    </div>
</div>
    <sec:csrfInput/>
</form>
<div class="day-of-week">
    <div>Mo</div>
    <div>Tu</div>
    <div>We</div>
    <div>Th</div>
    <div>Fr</div>
    <div>Sa</div>
    <div>Su</div>
</div>

<form method="post" action="/calendar/dayDetails">
    <div class="date-grid">
        <c:forEach items="${calendarProperties.daysOfMonth}" var="dayOfMonth">
            <button type="submit" value="${dayOfMonth.value}" name="date">${dayOfMonth.key}</button>
        </c:forEach>
    </div>
    <sec:csrfInput/>
</form>
</body>
</html>
