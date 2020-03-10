<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 16.02.2020
  Time: 09:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Ingredient Page</title>
</head>
<body>
<h1>Add new ingredient</h1>
<div id="container">
    <f:form modelAttribute="ingredientDTO" method="post" action="/ingredient">
        <div class="ingredientName">
            <div class="">
                <f:label path="ingredientName" id="igredientName">ingredient name*</f:label>
            </div>
            <div class="">
                <f:input path="ingredientName" value="${ingredientDTO.ingredientName}" required="true"/>
            </div>
            <div class="error">
                <f:errors path="ingredientName"/>
            </div>
        </div>

        <div class="caloriesPer100g">
            <div class="label">
                <f:label path="caloriesPer100g"> amount calories per 100g </f:label>
            </div>
            <div class="text-input">
                <f:input type="number" path="caloriesPer100g" value="${ingredientDTO.caloriesPer100g}" required="true"/>
            </div>
            <div class="error">
                <f:errors path="caloriesPer100g"/>
            </div>
        </div>
        <c:choose>
            <c:when test="${not empty availableCategory}">
                <div class="">
                    <f:label path="categoryToAdd"> Select ingredient categories</f:label>
                    <f:select path="categoryToAdd" id="ingredientCategory">

                        <c:forEach items="${availableCategory}" var="ingrCategory">
                            <f:option value="${ingrCategory.id};${ingrCategory.name}">${ingrCategory.name}</f:option>

                        </c:forEach>

                    </f:select>
                    <button type="submit" name="add">+</button>
                </div>
            </c:when>
            <c:otherwise>
                No more categories
            </c:otherwise>
        </c:choose>
        <div><p>new category</p>
            <c:choose>
                <c:when test="${categoryToAddBoolean==true}">
                    <button type="submit" name="back">back</button>
                </c:when>
                <c:otherwise>
                    <button type="submit" name="addNewCategory">add</button>
                </c:otherwise>
            </c:choose>

        </div>


        <c:if test="${categoryToAddBoolean==true}">
            <f:form modelAttribute="ingredientCategoryDTO" method="post" action="/ingredient">
                <div>
                    <div class="text-input">
                        <f:label path="name">New category:</f:label>
                    </div>
                    <div class="text-input">
                        <f:input path="name" id="categoryName" value="${ingredientCategoryDTO.name}"/>
                    </div>
                    <div class="error">
                        <f:errors path="name"/>
                    </div>
                    <button type="submit" name="addCategory">save</button>
                </div>
                <c:forEach items="${ingredientDTO.ingredientCategoriesIdAndName}" var="addedCategories">
                    <input type="hidden" value="${addedCategories.id};${addedCategories.name}"
                           name="ingredientCategoriesIdAndName"/>
                </c:forEach>
            </f:form>

        </c:if>

        Selected categories:
        <ol>
            <c:forEach items="${ingredientDTO.ingredientCategoriesIdAndName}" var="addedCategories">
                <div>
                    <li><span>${addedCategories.name}</span><span><button type="submit" name="categoryToRemove"
                                                                          value="${addedCategories.id}">-</button>
               </span></li>
                </div>
                <input type="hidden" value="${addedCategories.id};${addedCategories.name}"
                       name="ingredientCategoriesIdAndName"/>

            </c:forEach>

            <sec:csrfInput/>
        </ol>
        <f:errors path="ingredientCategoriesIdAndName" />
        <input type="submit" value="send" name="send"/>
        <div class="">

        </div>

    </f:form>

</div>

</body>

</html>