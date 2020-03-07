<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-28
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>MealDetails</title>
</head>
<body>
<div>
    <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
</div>

<div class="content">
    <c:if test="${hasMealPicture}">
        <img src="/createMeal/meal-file?mealFileId=${mealDetails.mealFile.id}" height="200"/>
    </c:if>
</div>
<div><label for="mealName">Name:</label></div>
<input id="mealName" name="name" type="text" value="${mealDetails.name}">
<div class="col-25">
    <label for="mealDescription">Recipe</label>
</div>
<div class="col-75">
    <textarea id="mealDescription" name="mealDescription" rows="4" cols="50" readonly>${mealDetails.recipe}</textarea>
</div>

<div>
    <label for="preparationTimeInMinutes">PREPARATION TIME:</label>
</div>
<input id="preparationTimeInMinutes" name="preparationTimeInMinutes" type="number"
       value="${mealDetails.preparationTime}" readonly/>
</body>
<div>
    <label for="calories">CALORIES:</label>
</div>
<input id="calories" name="calories" type="number"
       value="${mealDetails.calories}" readonly/>
</body>
<div>
    <label>MealTypes:</label>
</div>
<div>
    <c:forEach items="${mealTypes}" var="mealType">
        ${mealType}
    </c:forEach>
</div>
<div>
    <label>PartsOfMeal:</label>
</div>
<div>
    <c:forEach items="${partsOfMeal}" var="partOfMeal">
        ${partOfMeal.ingredient.name} ${partOfMeal.amount}
    </c:forEach>
</div>

<div>
    <label>Created by:</label>
</div>
<div>
    <input value="${mealDetails.creatorUser.username}" name="creatorUsername" readonly>
</div>

</html>
