<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: akprz
  Date: 26.02.2020
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modification created daily meal's set</title>
</head>
<body>
<h1>Crate new daily diet set </h1>
<div id="container">
    <form method="post" action="/createDailySet">

        <h3> Amount meals per day </h3>
        <div>
            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked==null}">
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
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${dailySetDTO.mealAmount !=5 }">
        <span><input type="radio" id="mealAmountChoice3c" name="mealAmount" value="3" checked disabled>
        <label for="mealAmountChoice3c">3 (breakfast, diner, supper)</label></span>
                            <input type="hidden" value="3" name="mealAmount">

                            <span> <input type="radio" id="mealAmountChoice3" name="mealAmount" value="5" disabled>
        <label for="mealAmountChoice3"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>
                        </c:when>
                        <c:otherwise>
        <span><input type="radio" id="mealAmountChoice4" name="mealAmount" value="3" disabled>
        <label for="mealAmountChoice4">3 (breakfast, diner, supper)</label></span>
                            <input type="hidden" value="5" name="mealAmount">

                            <span> <input type="radio" id="mealAmountChoice4c" name="mealAmount" value="5" checked
                                          disabled>
        <label for="mealAmountChoice4c"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>

                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>

        <div>
            <h3> How many calories contain your set:</h3>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==1000 }">
<span><input type="radio" id="caloriesAmount1c" name="caloriesPicked" value="1000" checked>
        <label for="caloriesAmount1c">1000[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount1" name="caloriesPicked" value="1000">
        <label for="caloriesAmount1">1000[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==1500 }">
<span><input type="radio" id="caloriesAmount2c" name="caloriesPicked" value="1500" checked>
    <label for="caloriesAmount2c"> 1500[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount2" name="caloriesPicked" value="1500">
    <label for="caloriesAmount2"> 1500[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==2000 }">
<span><input type="radio" id="caloriesAmount3c" name="caloriesPicked" value="2000" checked>
    <label for="caloriesAmount3c"> 2000[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount3" name="caloriesPicked" value="2000">
    <label for="caloriesAmount3"> 2000[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==2500 }">
<span><input type="radio" id="caloriesAmount4c" name="caloriesPicked" value="2500" checked>
    <label for="caloriesAmount4c"> 2500[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount4" name="caloriesPicked" value="2500">
    <label for="caloriesAmount4"> 2500[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==3000 }">
<span><input type="radio" id="caloriesAmount5c" name="caloriesPicked" value="3000" checked>
    <label for="caloriesAmount5c"> 3000[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount5" name="caloriesPicked" value="3000">
    <label for="caloriesAmount5"> 3000[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==3500 }">
<span><input type="radio" id="caloriesAmount6c" name="caloriesPicked" value="3500" checked>
    <label for="caloriesAmount6c"> 3500[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount6" name="caloriesPicked" value="3500">
    <label for="caloriesAmount6"> 3500[kcal]</label></span>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked ==4000 }">
<span><input type="radio" id="caloriesAmount7c" name="caloriesPicked" value="4000" checked>
    <label for="caloriesAmount7c"> 4000[kcal]</label></span>
                </c:when>
                <c:otherwise>
        <span><input type="radio" id="caloriesAmount7" name="caloriesPicked" value="4000">
    <label for="caloriesAmount7"> 4000[kcal]</label></span>
                </c:otherwise>
            </c:choose>
            <c:if test="${dailySetDTO.caloriesPicked==null}">
            </c:if>


            <c:choose>
                <c:when test="${dailySetDTO.caloriesPicked!=null}">
                    <c:choose>
                        <c:when test="${!dailySetDTO.mealPicked}">
                            <div>
                                <div>
                                    <label for="breakfast"> Pick breakfast </label>
                                    <select name="meals" id="breakfast">
                                        <c:forEach items="${availableMeats.breakfast}" var="meal">
                                            <c:choose>
                                                <c:when test="${dailySetDTO.meals.size()!=0}">
                                                    <c:choose>
                                                        <c:when test="${dailySetDTO.meals.get(0).id==meal.id}">
                                                            <option value="${meal.id};${meal.name};${meal.calories};BREAKFAST"
                                                                    selected>${meal.name}- ${meal.calories}[kcal]
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${meal.id};${meal.name};${meal.calories};BREAKFAST">${meal.name}- ${meal.calories}[kcal]</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${meal.id};${meal.name};${meal.calories};BREAKFAST">${meal.name}- ${meal.calories}[kcal]</option>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>
                                    </select>
                                </div>
                                <c:choose>
                                    <c:when test="${dailySetDTO.mealAmount ==5 }">
                                        <div>
                                            <label for="secondBreakfast"> Pick second breakfast </label>
                                            <select name="meals" id="secondBreakfast">
                                                <c:forEach items="${availableMeats.secondBreakfast}" var="meal">

                                                    <c:choose>
                                                        <c:when test="${dailySetDTO.meals.size()!=0}">
                                                            <c:choose>
                                                                <c:when test="${dailySetDTO.meals.get(3).id==meal.id}">
                                                                    <option value="${meal.id};${meal.name};${meal.calories};SECOND_BREAKFAST"
                                                                            selected>${meal.name}- ${meal.calories}[kcal]
                                                                    </option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${meal.id};${meal.name};${meal.calories};SECOND_BREAKFAST">${meal.name}- ${meal.calories}[kcal]</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${meal.id};${meal.name};${meal.calories};SECOND_BREAKFAST">${meal.name}- ${meal.calories}[kcal]</option>
                                                        </c:otherwise>
                                                    </c:choose>
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
                                            <c:choose>
                                                <c:when test="${dailySetDTO.meals.size()!=0}">
                                                    <c:choose>
                                                        <c:when test="${dailySetDTO.meals.get(1).id==meal.id}">
                                                            <option value="${meal.id};${meal.name};${meal.calories};DINNER"
                                                                    selected>${meal.name}- ${meal.calories}[kcal]
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${meal.id};${meal.name};${meal.calories};DINNER">${meal.name}- ${meal.calories}[kcal]</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${meal.id};${meal.name};${meal.calories};DINNER">${meal.name}- ${meal.calories}[kcal]</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <c:choose>
                                    <c:when test="${dailySetDTO.mealAmount ==5 }">
                                        <div>
                                            <label for="tea"> Pick tea </label>
                                            <select name="meals" id="tea">
                                                <c:forEach items="${availableMeats.tea}" var="meal">
                                                    <c:choose>
                                                        <c:when test="${dailySetDTO.meals.size()!=0}">
                                                            <c:choose>
                                                                <c:when test="${dailySetDTO.meals.get(4).id==meal.id}">
                                                                    <option value="${meal.id};${meal.name};${meal.calories};TEA"
                                                                            selected>${meal.name}- ${meal.calories}[kcal]
                                                                    </option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${meal.id};${meal.name};${meal.calories};TEA">${meal.name}- ${meal.calories}[kcal]</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${meal.id};${meal.name};${meal.calories};TEA">${meal.name}- ${meal.calories}[kcal]</option>
                                                        </c:otherwise>
                                                    </c:choose>
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
                                            <c:choose>
                                                <c:when test="${dailySetDTO.meals.size()!=0}">
                                                    <c:choose>
                                                        <c:when test="${dailySetDTO.meals.get(2).id==meal.id}">
                                                            <option value="${meal.id};${meal.name};${meal.calories};SUPPER"
                                                                    selected>${meal.name}- ${meal.calories}[kcal]
                                                            </option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${meal.id};${meal.name};${meal.calories};SUPPER">${meal.name}- ${meal.calories}[kcal]</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${meal.id};${meal.name};${meal.calories};SUPPER">${meal.name}- ${meal.calories}[kcal]</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>

                                    </select>
                                </div>

                                <div>
                                    <input type="hidden" value="true" name="mealPicked">
                                    <input type="submit" name="filter" value="Check"/>
                                    <button type="submit" name="clear">RESET ALL</button>
                                    <sec:csrfInput/>
                                </div>
                            </div>

                        </c:when>
                        <c:otherwise>
                            <h3> Your meals' daily set is: </h3>
                            <p>
                                breakfast is ${dailySetDTO.meals.get(0).name}
                                calories: ${dailySetDTO.meals.get(0).calories} [kcal]
                            </p>
                            <input type="hidden"
                                   value="${dailySetDTO.meals.get(0).id};${dailySetDTO.meals.get(0).name};${dailySetDTO.meals.get(0).calories};BREAKFAST"
                                   name="meals">
                            <c:choose>
                                <c:when test="${dailySetDTO.meals.size()==5}">
                                    <p>
                                        second breakfast is ${dailySetDTO.meals.get(3).name}
                                        calories: ${dailySetDTO.meals.get(3).calories} [kcal]
                                    </p>
                                    <input type="hidden"
                                           value="${dailySetDTO.meals.get(3).id};${dailySetDTO.meals.get(3).name};${dailySetDTO.meals.get(3).calories};SECOND_BREAKFAST"
                                           name="meals">
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>
                            <p>
                                dinner is ${dailySetDTO.meals.get(1).name}
                                calories: ${dailySetDTO.meals.get(1).calories}[kcal]
                            </p>
                            <input type="hidden"
                                   value="${dailySetDTO.meals.get(1).id};${dailySetDTO.meals.get(1).name};${dailySetDTO.meals.get(1).calories};DINNER"
                                   name="meals">


                            <c:choose>
                                <c:when test="${dailySetDTO.meals.size()==5}">
                                    <p> tea is ${dailySetDTO.meals.get(4).name}
                                        calories: ${dailySetDTO.meals.get(4).calories}[kcal
                                        ]</p>
                                    <input type="hidden"
                                           value="${dailySetDTO.meals.get(4).id};${dailySetDTO.meals.get(4).name};${dailySetDTO.meals.get(4).calories};TEA"
                                           name="meals">

                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                            </c:choose>

                            <p> supper is ${dailySetDTO.meals.get(2).name}
                                calories: ${dailySetDTO.meals.get(2).calories}
                                [kcal]
                            </p>
                            <input type="hidden"
                                   value="${dailySetDTO.meals.get(2).id};${dailySetDTO.meals.get(2).name};${dailySetDTO.meals.get(2).calories};SUPPER"
                                   name="meals">

                            <h3> summary calories is ${dailySetDTO.calories} [kcal]</h3>
                            <c:if test="${dailySetDTO.caloriesPicked<dailySetDTO.calories}">
                                <p style="color: red">Too manygit branch calories!!!! You try create meal set with ${dailySetDTO.caloriesPicked} [kcal], but your set have ${dailySetDTO.calories} [kcal] </p>
                            </c:if>

                            <c:choose>
                                <c:when test="${redirected == true}">
                                    <div>
                                        <input type="hidden" name="dietDay" value="${dietDay}">
                                        <button type="submit" name="createdForDiet">Create for diet</button>
                                        <sec:csrfInput/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <button type="submit" name="send">SEND</button>
                                        <button type="submit" name="modifyMealList">MODIFY LIST</button>
                                        <button type="submit" name="clear">RESET ALL</button>
                                        <sec:csrfInput/>
                                    </div>
                                </c:otherwise>
                            </c:choose>


                        </c:otherwise>


                    </c:choose>

                </c:when>
                <c:otherwise>
                    <div>
                        <input type="submit" name="filter" value="Filter"/>
                        <sec:csrfInput/>
                    </div>


                </c:otherwise>
            </c:choose>
        </div>
    </form>

</di

</body>
</html>
