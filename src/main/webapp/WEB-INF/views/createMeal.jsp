<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-16
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>CreateMeal</title>
</head>
<body>
CreateMeal
<div class="container">
    <form method="post" action="/createMeal">
        <div><label for="mealName">Name:</label></div>
        <input id="mealName" name="mealName" type="text">
        <div class="col-25">
            <label for="mealDescription">Recipe</label>
        </div>
        <div class="col-75">
            <textarea id="mealDescription" name="mealDescription" rows="4" cols="50"></textarea>
        </div>

        <div class="col-25">
            <label for="ingredientId">Add ingredient</label>
        </div>
        <div>
            <select id="ingredientId" name="ingredientId">
                <c:forEach items="${ingredients}" var="ingredient">
                    <option value="${ingredient.id}">${ingredient.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-25">
            <label for="ingredientAmount">Amount</label>
        </div>
        <div class="col-75">
            <input id="ingredientAmount" name="ingredientAmount" type="number"/>
        </div>
        <input type="submit" name="add" value=" + "/>
        <div>
            <label>Meal ingredients: </label>
        </div>
        <div>
            <ul>
                <c:forEach items="${mealDTO.mealIngredients}" var="mealIngredient">
                    <li>${mealIngredient.ingredient.name} ${mealIngredient.amount}</li>
                    <input type="hidden" name="mealIngredients" value="${mealIngredient.id}"/>
                </c:forEach>
            </ul>
        </div>
        <input type="submit" value="send" name="send"/>
        <sec:csrfInput/>
    </form>
</div>
</body>
</html>
