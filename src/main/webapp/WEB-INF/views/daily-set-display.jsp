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
        <th COLSPAN="2">Diner</th>
        <th COLSPAN="2">TEA</th>
        <th COLSPAN="2">SUPPER</th>
        <th>ACTION</th>
    </tr>
    <c:forEach items="${createdDailySet}" var="dailySet">
        <tr>
            <td>${dailySet.calories} [kcal]</td>
            <td>
                    <%--                    ${dailySet.mealForDailyDTO.get(0).mealFile}--%>
            </td>
            <td>
                <div><p> Type: ${dailySet.mealForDailyDTO.get(0).mealTypeEnumeration} </p></div>
                <div><p> Meal name:${dailySet.mealForDailyDTO.get(0).name}</p></div>
                <div><p> Preparation time ${dailySet.mealForDailyDTO.get(0).preparationTime} min. </p></div>
                <div><p> Meal calories: ${dailySet.mealForDailyDTO.get(0).calories} [kcal] </p></div>
                    <%--                <div>${dailySet.mealForDailyDTO.get(3).recipe}</div>--%>
                <div><a href="/${dailySet.mealForDailyDTO.get(0).id}">View more</a></div>
            </td>
            <td>
                    <%--                    ${dailySet.mealForDailyDTO.get(1).mealFile}--%>
            </td>
            <td>
                <c:if test="${dailySet.mealAmount==5}">
                    <div><p> Type: ${dailySet.mealForDailyDTO.get(3).mealTypeEnumeration} </p></div>
                    <div><p> Meal name:${dailySet.mealForDailyDTO.get(3).name}</p></div>
                    <div><p> Preparation time ${dailySet.mealForDailyDTO.get(3).preparationTime} min. </p></div>
                    <div><p> Meal calories: ${dailySet.mealForDailyDTO.get(3).calories} [kcal] </p></div>
                    <%--                <div>${dailySet.mealForDailyDTO.get(3).recipe}</div>--%>
                    <div><a href="/${dailySet.mealForDailyDTO.get(3).id}">View more</a></div>
                </c:if>
            </td>
            <td>
                    <%--                    ${dailySet.mealForDailyDTO.get(2).mealFile}--%>
            </td>
            <td>
                <div><p> Type: ${dailySet.mealForDailyDTO.get(1).mealTypeEnumeration} </p></div>
                <div><p> Meal name:${dailySet.mealForDailyDTO.get(1).name}</p></div>
                <div><p> Preparation time ${dailySet.mealForDailyDTO.get(1).preparationTime} min. </p></div>
                <div><p> Meal calories: ${dailySet.mealForDailyDTO.get(1).calories} [kcal] </p></div>
                    <%--                <div>${dailySet.mealForDailyDTO.get(3).recipe}</div>--%>
                <div><a href="/${dailySet.mealForDailyDTO.get(1).id}">View more</a></div>
            </td>

            <td>

                    <%--                    ${dailySet.mealForDailyDTO.get(4).mealFile}--%>
            </td>
            <td>
                <c:if test="${dailySet.mealAmount==5}">
                    <div><p> Type: ${dailySet.mealForDailyDTO.get(4).mealTypeEnumeration} </p></div>
                    <div><p> Meal name:${dailySet.mealForDailyDTO.get(4).name}</p></div>
                    <div><p> Preparation time ${dailySet.mealForDailyDTO.get(4).preparationTime} min. </p></div>
                    <div><p> Meal calories: ${dailySet.mealForDailyDTO.get(4).calories} [kcal] </p></div>
                    <%--                <div>${dailySet.mealForDailyDTO.get(3).recipe}</div>--%>
                    <div><a href="/${dailySet.mealForDailyDTO.get(4).id}">View more</a></div>
                </c:if>
            </td>
            <td>
                    <%--                    ${dailySet.mealForDailyDTO.get(4).mealFile}--%>
            </td>
            <td>
                <div><p> Type: ${dailySet.mealForDailyDTO.get(2).mealTypeEnumeration} </p></div>
                <div><p> Meal name:${dailySet.mealForDailyDTO.get(2).name}</p></div>
                <div><p> Preparation time ${dailySet.mealForDailyDTO.get(2).preparationTime} min. </p></div>
                <div><p> Meal calories: ${dailySet.mealForDailyDTO.get(2).calories} [kcal] </p></div>
                    <%--                <div>${dailySet.mealForDailyDTO.get(3).recipe}</div>--%>
                <div><a href="/${dailySet.mealForDailyDTO.get(2).id}">View more</a></div>
            </td>
            <td>
                <div><a href="">View more</a></div>
                <c:if test="${dailySet.creatorUserName==loggedUser}">
                    <div><a href="/daily-set/modify/${dailySet.id}/modify">Modify</a></div>
                </c:if>
                <div><a href="">View more</a></div>
            </td>

        </tr>
    </c:forEach>
</table>


<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
