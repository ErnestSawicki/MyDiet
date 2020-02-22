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
<div>
    <jsp:include page="fragments/header.jsp"/>
</div>
<h1>PREPARE MEAL</h1>
<div class="container">
    <form method="post" action="/createMeal">
        <div><label for="mealName">Name:</label></div>
        <input id="mealName" name="name" type="text" required value="${mealCreateDTO.name}">
        <div class="col-25">
            <label for="mealDescription">Recipe</label>
        </div>
        <div class="col-75">
            <textarea id="mealDescription" name="mealDescription" rows="4" cols="50"  required>${mealCreateDTO.mealDescription}</textarea>
        </div>

        <div><label for="preparationTimeInMinutes">PREPARATION TIME:</label></div>
        <input id="preparationTimeInMinutes" name="preparationTimeInMinutes" type="number" value="${mealCreateDTO.preparationTimeInMinutes}" required>


        <div class="col-25">
            <label for="ingredientId">Add ingredient</label>
        </div>
        <div>
            <select id="ingredientId" name="ingredientToAdd">
                <c:forEach items="${availableIngredients}" var="ingredient">
                    <option value="${ingredient.id};${ingredient.name};0">${ingredient.name}</option>
                </c:forEach>
            </select>

        </div>
        <div class="col-25">
            <label for="ingredientAmount">Amount</label>
        </div>
        <div class="col-75">
            <input id="ingredientAmount" name="ingredientToAddAmount" type="number"/>
        </div>
        <input type="submit" name="add" value=" + "/>
        <div>
            <label>Meal ingredients: </label>
        </div>
        <div>
            <ul>
                <c:forEach items="${mealCreateDTO.partsOfMealIngredientIdNameAmount}" var="mealIngredient" varStatus="lp">
                <li>${lp.count} name: ${mealIngredient.name} amount: ${mealIngredient.ingredientAmount}
                    <button type="submit" name="ingredientToRemove" value=${mealIngredient.ingredientId} > Delete </button></li>
                    <input type="hidden" name="partsOfMealIngredientIdNameAmount" value="${mealIngredient.ingredientId};${mealIngredient.name};${mealIngredient.ingredientAmount}"/>
                </c:forEach>
            </ul>
            <p>currency calories = ${mealCreateDTO.calories} </p>
        </div>
        <div class="mealType">
            <select>
                <c:forEach items="${mealTypes}" var="mealType">
                    <option ${mealType.mealTypeName}>${mealType.mealTypeName}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="send" name="send"/>
        <sec:csrfInput/>

    </form>
</div>
<div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
