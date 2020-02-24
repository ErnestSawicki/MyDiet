<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 21.02.2020
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Daily meals set</title>
</head>
<body>

<h1>Crate new daily diet set </h1>
<div id="container">
    <form method="post" action="/createDailySet">

        <h3> Amount meals per day </h3>
        <div>
            <c:choose>
                <c:when test="${dailySetDTO.mealAmount !=5 }">
        <span><input type="radio" id="mealAmountChoice1c" name="mealAmount" value="3" checked>
        <label for="mealAmountChoice1c">3 (breakfast, diner, supper)</label></span>

                    <span> <input type="radio" id="mealAmountChoice2" name="mealAmount" value="5">
        <label for="mealAmountChoice2"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="mealAmountChoice1" name="mealAmount" value="3">
        <label for="mealAmountChoice1">3 (breakfast, diner, supper)</label></span>

                    <span> <input type="radio" id="mealAmountChoice2c" name="mealAmount" value="5" checked>
        <label for="mealAmountChoice2c"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>
                </c:otherwise>
            </c:choose>
        </div>

        <div>
            <h3> How many calories contain your set:</h3>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==1000 }">
<span><input type="radio" id="caloriesAmount1c" name="calories" value="1000" checked>
        <label for="caloriesAmount1c">1000[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount1" name="calories" value="1000">
        <label for="caloriesAmount1">1000[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==1500 }">
<span><input type="radio" id="caloriesAmount2c" name="calories" value="1500" checked>
    <label for="caloriesAmount2c"> 1500[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount2" name="calories" value="1500">
    <label for="caloriesAmount2"> 1500[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==2000 }">
<span><input type="radio" id="caloriesAmount3c" name="calories" value="2000" checked>
    <label for="caloriesAmount3c"> 2000[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount3" name="calories" value="2000">
    <label for="caloriesAmount3"> 2000[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==2500 }">
<span><input type="radio" id="caloriesAmount4c" name="calories" value="2500" checked>
    <label for="caloriesAmount4c"> 2500[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount4" name="calories" value="2500">
    <label for="caloriesAmount4"> 2500[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==3000 }">
<span><input type="radio" id="caloriesAmount5c" name="calories" value="3000" checked>
    <label for="caloriesAmount5c"> 3000[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount5" name="calories" value="3000">
    <label for="caloriesAmount5"> 3000[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==3500 }">
<span><input type="radio" id="caloriesAmount6c" name="calories" value="3500" checked>
    <label for="caloriesAmount6c"> 3500[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount6" name="calories" value="3500">
    <label for="caloriesAmount6"> 3500[cal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.calories ==4000 }">
<span><input type="radio" id="caloriesAmount7c" name="calories" value="4000" checked>
    <label for="caloriesAmount7c"> 4000[cal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount7" name="calories" value="4000">
    <label for="caloriesAmount7"> 4000[cal]</label></span>
                </c:otherwise>
            </c:choose>
            <input type="submit" name="filter" value="Filter"/>

        </div>
        <div>

            <div>
                <label for="breakfast"> Pick breakfast </label>
                <select name="meals" id="breakfast">
                    <c:forEach items="${availableMeats.breakfast}" var="meal">
                        <option value="${meal.id};${meal.name};${meal.calories},BREAKFAST">${meal.name} - ${meal.calories}kcal</option>
                    </c:forEach>
                </select>
            </div>
            <c:choose>
                <c:when test="${dailySetDTO.mealAmount ==5 }">
                    <div>
                        <label for="secondBreakfast"> Pick second breakfast </label>
                        <select name="meals" id="secondBreakfast">
                            <c:forEach items="${availableMeats.secondBreakfast}" var="meal">
                                <option value="${meal.id};${meal.name};${meal.calories};SECOND_BREAKFAST">${meal.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:when>
                <c:otherwise> <br/>
                </c:otherwise>
            </c:choose>

            <div>
                <label for="dinner"> Pick dinner </label>
                <select name="meals" id="dinner">
                    <c:forEach items="${availableMeats.dinner}" var="meal">
                        <option value="${meal.id};${meal.name};${meal.calories};DINNER">${meal.name}</option>
                    </c:forEach>
                </select>
            </div>

            <c:choose>
                <c:when test="${dailySetDTO.mealAmount ==5 }">
                    <div>
                        <label for="tea"> Pick tea </label>
                        <select name="meals" id="tea">
                            <c:forEach items="${availableMeats.tea}" var="meal">
                                <option value="${meal.id};${meal.name};${meal.calories};TEA">${meal.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:when>
                <c:otherwise> <br/>
                </c:otherwise>
            </c:choose>
            <div>
                <label for="supper"> Pick supper </label>
                <select name="meals" id="supper">
                    <c:forEach items="${availableMeats.supper}" var="meal">
                        <option value="${meal.id};${meal.name};${meal.calories}SUPPER">${meal.name}</option>
                    </c:forEach>
                </select>
            </div>
            <c:choose>
                <c:when test="${redirected == true}">
                    <div>
                        <button type="submit" name="createdForDiet">Create</button>
                        <sec:csrfInput/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <button type="submit" name="send">SEND</button>
                        <sec:csrfInput/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <sec:csrfInput/>
    </form>

    <h3> Your meals' daily set is: </h3>
    <h1>ToDo</h1>
    <%--            <p> breakfast is ${dailySetDTO.simpleMealsDTO.get(0).name}
                    calories: ${dailySetDTO.simpleMealsDTO.get(0).calories} [kcal]</p>
                <c:choose>
                <c:when test="${dailySetDTO.mealAmount ==5 }">
                <p> second breakfast is ${dailySetDTO.simpleMealsDTO.get(3)}
                    calories: ${dailySetDTO.simpleMealsDTO.get(3).calories} </p>>
                </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>
                <p> dinner is ${dailySetDTO.simpleMealsDTO.get(1)} calories: ${dailySetDTO.simpleMealsDTO.get(1).calories}
                    [kcal]</p>
                <c:choose>
                <c:when test="${dailySetDTO.mealAmount ==5 }">
                <p> tea is ${dailySetDTO.simpleMealsDTO.get(4)} calories: ${dailySetDTO.simpleMealsDTO.get(4).calories}
                    [kcal]</p>
                </c:when>
                    <c:otherwise>></c:otherwise>
                </c:choose>

                <p> supper is ${dailySetDTO.simpleMealsDTO.get(2)} calories: ${dailySetDTO.simpleMealsDTO.get(2).calories}
                    [kcal]</p>

                <h3> summary calories is ${dailySetDTO.calories} [kcal]</h3>--%>

</body>
</html>
