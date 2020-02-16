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
        <div class="col-25">
            <label for="recipe">Recipe</label>
        </div>
        <div class="col-75">
            <textarea id="recipe" rows="4" cols="50"></textarea>
        </div>

        <div class="col-25">
            <label for="ingredient">Add ingredient</label>
        </div>
        <div>
            <select id="ingredient" name="ingredient">
                <c:forEach items="${ingredients}" var="ingredient">
                    <option value="${ingredient}">${ingredient.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-25">
            <label for="amount">Amount</label>
        </div>
        <div class="col-75">
            <input id="amount" name="amount" type="number"/>
        </div>
        <input type="submit" name="add" value=" + "/>
        <div>
            <label>Meal ingredients: </label>
        </div>
        <div>
            <ul>
                <c:forEach items="${mealDTO.addedIngredients}" var="mealIngredient">
                    <li>${mealIngredient.name} ${mealIngredient.amount}</li>
                </c:forEach>
            </ul>
        </div>
        <input type="submit" value="send" name="send"/>
    </form>
</div>
</body>
</html>
