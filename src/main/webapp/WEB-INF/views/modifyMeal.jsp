<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-24
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ModifyMeal</title>
</head>
<body>
<form method="post" action="/createMeal/modifyMeal">
    <label>MealPicture</label>
    <div class="content">
        <c:if test="${hasMealPicture}">
            <img src="/createMeal/meal-file?mealFileId=${mealToModify.mealFile.id}"/>
        </c:if>
    </div>
    <div><label for="mealName">Name:</label></div>
    <input id="mealName" name="name" type="text" value="${mealToModify.name}">
    <div class="col-25">
        <label for="mealDescription">Recipe</label>
    </div>
    <div class="col-75">
            <textarea id="mealDescription" name="mealDescription" rows="4" cols="50" required>${mealToModify.recipe}</textarea>
    </div>

    <div><label for="preparationTimeInMinutes">PREPARATION TIME:</label></div>
    <input id="preparationTimeInMinutes" name="preparationTimeInMinutes" type="number"
           value="${mealToModify.preparationTime}" required>

</form>

</body>
</html>
