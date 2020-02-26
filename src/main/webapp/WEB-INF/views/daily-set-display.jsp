<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 26.02.2020
  Time: 04:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All daily meal's set</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>

<h1>
    All available created meal's daily set
</h1>
<table>
    <tr>
        <th>Calories</th>
        <th COLSPAN="2">BREAKFAST</th>
        <th COLSPAN="2">SECOND BREAKFAST</th>
        <th COLSPAN="2">BREAKFAST</th>
        <th COLSPAN="2">BREAKFAST</th>
        <th>ACTION</th>
    </tr>
    <c:forEach items="${createdDailySet}" var="dailySet">
        <tr>
            <td>${dailySet.calories}</td>
            <td>${dailySet.mealTime.get(0).meal.mealFile}</td>
            <td>
                <div>${dailySet.mealTime.get(0).mealTypeName}</div>
                <div>${dailySet.mealTime.get(0).meal.name}</div>
                <div>${dailySet.mealTime.get(0).meal.preparationTime}</div>
                <div>${dailySet.mealTime.get(0).meal.recipe}</div>
                <div><a href="">View more</a></div>
            </td>
            <td>${dailySet.mealTime.get(1).meal.mealFile}</td>
            <td>
                <div>${dailySet.mealTime.get(1).mealTypeName}</div>
                <div>${dailySet.mealTime.get(1).meal.name}</div>
                <div>${dailySet.mealTime.get(1).meal.preparationTime}</div>
                <div>${dailySet.mealTime.get(1).meal.recipe}</div>
                <div><a href="/${dailySet.mealTime.get(1).id}">View more</a></div>
            </td>
            <td>${dailySet.mealTime.get(2).meal.mealFile}</td>
            <td>
                <div>${dailySet.mealTime.get(2).mealTypeName}</div>
                <div>${dailySet.mealTime.get(2).meal.name}</div>
                <div>${dailySet.mealTime.get(2).meal.preparationTime}</div>
                <div>${dailySet.mealTime.get(2).meal.recipe}</div>
                <div><a href="/${dailySet.mealTime.get(2).id}">View more</a></div>
            </td>
            nom

            <td>${dailySet.mealTime.get(3).meal.mealFile}</td>
            <td>
                <div>${dailySet.mealTime.get(3).mealTypeName}</div>
                <div>${dailySet.mealTime.get(3).meal.name}</div>
                <div>${dailySet.mealTime.get(3).meal.preparationTime}</div>
                <div>${dailySet.mealTime.get(3).meal.recipe}</div>
                <div><a href="/${dailySet.mealTime.get(3).id}">View more</a></div>
            </td>
            <td>${dailySet.mealTime.get(4).meal.mealFile}</td>
            <td>
                <div>${dailySet.mealTime.get(4).mealTypeName}</div>
                <div>${dailySet.mealTime.get(4).meal.name}</div>
                <div>${dailySet.mealTime.get(4).meal.preparationTime}</div>
                <div>${dailySet.mealTime.get(4).meal.recipe}</div>
                <div><a href="/${dailySet.mealTime.get(4).id}">View more</a></div>
            </td>
            <td>
                <div><a href="">View more</a></div>
                <c:if test="${dailySet.creatorUser.username==loggedUser}">
                    <div><a href="/${dailySet.id}}">Modify</a></div>
                </c:if>
                <div><a href="">View more</a></div>
            </td>

        </tr>
    </c:forEach>
</table>


<jsp:include page="fragments/footer.jsp"/>

</body>
</html>
