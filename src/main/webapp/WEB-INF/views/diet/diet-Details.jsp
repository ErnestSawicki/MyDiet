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
    <style>
        .dietGeneral{
            align-content: center;
        }
    </style>
</head>
<body>
<div>
    <div>
        <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
    </div>
    <div class="dietGeneral">
        <div class="title">
            <p>Diet name: ${dietDetails.dietName}</p>
        </div>
        <div>
            <p>Description: ${dietDetails.description}</p>
        </div>
        <div>
            <p>Duration: ${dietDetails.duration}days</p>
        </div>
        <div>
            <p>Created by: ${dietDetails.creatorUser.username}</p>
        </div>
    </div>
    <div>
        <div>
            <label>DailySets</label>
        </div>
        <div>
            <c:forEach items="${dietDetails.dailySets}" var="dailySet" varStatus="iter">
                <div class="diet">
                    <p>Day ${iter.count}</p>
                    <c:forEach items="${dailySet.mealForDailyDTO}" var="meal">
                        <div class="dietDay">
                            <p>MealType: ${meal.mealTypeEnumeration.name()}</p>
                            <p>Name: ${meal.name}</p>
                            <p>kcal: ${meal.calories}</p>
                            <a href="/createMeal/viewMeal?mealId=${meal.id}" class="button">Meal Details</a>
                        </div>
                    </c:forEach>
                </div>

            </c:forEach>
        </div>
    </div>


</body>
</html>
