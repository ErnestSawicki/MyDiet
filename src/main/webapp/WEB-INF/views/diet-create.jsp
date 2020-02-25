<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-23
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create diet</title>
</head>
<body>
<form method="post" action="/createDiet">
<div>
    <label for="dietName">Diet name</label>
    <input type="text" id="dietName" name="dietName" value="${dietConfigurator.dietName}"/>
</div>
    <div>
    <label for="description">Description</label>
    <textarea cols="30" rows="5" id="description" name="description" >${dietConfigurator.dietDescription}</textarea>
    </div>
    <div>
    <label for="duration">Duration</label>
    <input type="number" id="duration" name="duration" value="${dietConfigurator.duration}"/>
    </div>
    <div>
    <input type="submit" name="filter" value="filter"/>
    </div>
    <p1>Kolejne dni diety</p1>
    <c:choose>
        <c:when test="${dailyMealSetsDTO != null}">
            <c:forEach items="${dailyMealSetsDTO}" var="dailySet" varStatus="loop">
                <div>
                    Day ${dailySet.key}:
                    <input type="submit" name="findDailySet" value="Find dailySet">
                    <a href="/createDailySet/forDiet?dietDay=${dailySet.key}">Create new dailySet</a>
                </div>
            </c:forEach>
        </c:when>
    </c:choose>
    <button type="submit" name="create">CreateDiet</button>
    <sec:csrfInput/>
</form>
</body>
</html>
