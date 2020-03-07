<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-07
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 300px;
            margin: auto;
            text-align: center;
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        a.button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 95%;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            font-size: 22px;
            color: black;
        }

        a.button:hover, a:hover {
            opacity: 0.7;
        }


    </style>
</head>
<body>
<div class="header">
    <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
</div>
<div class="wrapper">
    <c:forEach items="${meals}" var="meal">
        <div class="card">
            <div>
                <c:if test="${meal.mealFile != null}">
                    <img src="/createMeal/meal-file?mealFileId=${meal.mealFile.id}" style="width: 100%"/>
                </c:if>
            </div>
            <div class="title">
                <p1>Name: ${meal.name}</p1>
            </div>
            <div>
                <p1>Recipe: ${meal.recipe}</p1>
            </div>
            <div>
                <p1>Calories: ${meal.calories}</p1>
            </div>
            <div>
                <p1>Preparation time: ${meal.preparationTime}min</p1>
            </div>
            <div>
                <p1>Created by: ${meal.creatorUser.username}</p1>
            </div>

            <a href="/createMeal/viewMeal?mealId=${meal.id}" class="button">Details</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
