<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 16.02.2020
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Ingredient Page</title>
</head>
<body>
<h1>Add new ingredient</h1>
<div id="container">
    <form method="post" action="/ingredient">

        <div class="">
            <labe for="ingredientName">ingredient</labe>
        </div>
        <div class="">
            <input type="text" name="ingredientName" id="ingredientName">
        </div>

        <div class="">
            <labe for="caloriesPer100g">amount calories per 100</labe>
        </div>
        <div class="">
            <input type="number" name="caloriesPer100g" id="caloriesPer100g">
        </div>
        <c:choose>
            <c:when test="${not empty availableCategory}">
                <div class="">
                    <labe for="ingredientCategory">Select ingredient categories</labe>
                    <select name="categoryToAdd" id="ingredientCategory">

                        <c:forEach items="${availableCategory}" var="ingrCategory">
                            <option value="${ingrCategory.id};${ingrCategory.name}">${ingrCategory.name}</option>

                        </c:forEach>

                        <sec:csrfInput/>

                    </select>
                    <button type="submit" name="add">+</button>
                </div>

            </c:when>
            <c:otherwise>
                Brak kategorii
            </c:otherwise>
        </c:choose>


        Selected categories:
        <ol>
            <c:forEach items="${ingredientDTO.ingredientCategoriesIdAndName}" var="addedCategories">
                <div><li><span>${addedCategories.name}</span><span><button type="submit" name="categoryToRemove" value="${addedCategories.id}">-</button>
               </span></li></div>
                </li>
                <input type="hidden" value="${addedCategories.id};${addedCategories.name}"
                       name="ingredientCategoriesIdAndName"/>

            </c:forEach>

            <sec:csrfInput/>
        </ol>

        <input type="submit" value="send" name="send"/>
        <div class="">

        </div>

    </form>

</div>

</body>
</html>
