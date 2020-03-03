<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 28.02.2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm delete this meal set</title>
</head>
<body>
<jsp:include page="../fragments/header.jsp"/>
<div id="container">
    <h1> Do You really want delete this daily set? </h1>


    <h2> Generally parameters of daily set </h2>
    <p>calories: ${dailySetDTO.caloriesPicked} [kcal]</p>
    <p>exact amount calories: ${dailySetDTO.calories} [kcal]</p>
    <p>amount of meals: ${dailySetDTO.mealAmount}</p>

    <h2> Meals: </h2>
    <h3>Breakfast</h3>
    <p> meal's name: ${dailySetDTO.meals.get(0).name}</p>
    <p>amount of calories for meal: ${dailySetDTO.meals.get(0).calories}</p>
    <br/>
    <br/>
    <c:if test="${dailySetDTO.meals.size()==5}">
    <h3>Second breakfast</h3>
    <p> meal's name: ${dailySetDTO.meals.get(3).name}</p>
    <p>amount of calories for meal: ${dailySetDTO.meals.get(3).calories}</p>
    </c:if>
    <br/>
    <br/>
    <c:if test="${dailySetDTO.meals.size()==5}">
        <h3>Second breakfast</h3>
        <p> meal's name: ${dailySetDTO.meals.get(3).name}</p>
        <p>amount of calories for meal: ${dailySetDTO.meals.get(3).calories}</p>
    </c:if>

    <form method="post" action="/daily-set/modify">


    </form>
</div>
</body>
</html>
