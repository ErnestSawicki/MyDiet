<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:when test="${mealAmount !=5 }">
        <span><input type="radio" id="mealAmountChoice1c" name="mealAmount" value="3" checked>
        <label for="mealAmountChoice1c">3 (breakfast, diner, supper)</label></span>

        <span> <input type="radio" id="mealAmountChoice2" name="mealAmount" value="5">
        <label for="mealAmountChoice2"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>
    </c:when>
    <c:otherwise>
        <span><input type="radio" id="mealAmountChoice1" name="mealAmount" value="3" >
        <label for="mealAmountChoice1">3 (breakfast, diner, supper)</label></span>

        <span> <input type="radio" id="mealAmountChoice2c" name="mealAmount" value="5" checked>
        <label for="mealAmountChoice2c"> 5 (breakfast, second breakfast, dinner, tea, supper)</label></span>
    </c:otherwise>
</c:choose>
</div>
<br/>
<br/>
<div>
<h3> How many calories contain your set:</h3>

    <c:choose>
        <c:when test="${caloriesAmount ==1000 }">
<span><input type="radio" id="caloriesAmount1c" name="caloriesAmount" value="1000" checked>
        <label for="caloriesAmount1c">1000[cal]</label></span>
        </c:when>
    <c:otherwise>
        <span><input type="radio" id="caloriesAmount1" name="caloriesAmount" value="1000" >
        <label for="caloriesAmount1">1000[cal]</label></span>
    </c:otherwise>
    </c:choose>

    <c:choose>
    <c:when test="${caloriesAmount ==1500 }">
<span><input type="radio" id="caloriesAmount2c" name="caloriesAmount" value = "1500" checked>
    <label for="caloriesAmount2c"> 1500[cal]</label></span>
    </c:when>
    <c:otherwise>
        <span><input type="radio" id="caloriesAmount2" name="caloriesAmount" value = "1500">
    <label for="caloriesAmount2"> 1500[cal]</label></span>
    </c:otherwise>
</c:choose>

    <c:choose>
        <c:when test="${caloriesAmount ==2000 }">
<span><input type="radio" id="caloriesAmount3c" name="caloriesAmount" value = "2000" checked>
    <label for="caloriesAmount3c"> 2000[cal]</label></span>
        </c:when>
        <c:otherwise>
        <span><input type="radio" id="caloriesAmount3" name="caloriesAmount" value = "2000">
    <label for="caloriesAmount3"> 2000[cal]</label></span>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${caloriesAmount ==2500 }">
<span><input type="radio" id="caloriesAmount4c" name="caloriesAmount" value = "2500" checked>
    <label for="caloriesAmount4c"> 2500[cal]</label></span>
        </c:when>
        <c:otherwise>
        <span><input type="radio" id="caloriesAmount4" name="caloriesAmount" value = "2500">
    <label for="caloriesAmount4"> 2500[cal]</label></span>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${caloriesAmount ==3000 }">
<span><input type="radio" id="caloriesAmount5c" name="caloriesAmount" value = "3000" checked>
    <label for="caloriesAmount5c"> 3000[cal]</label></span>
        </c:when>
        <c:otherwise>
        <span><input type="radio" id="caloriesAmount5" name="caloriesAmount" value = "3000">
    <label for="caloriesAmount5"> 3000[cal]</label></span>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${caloriesAmount ==3500 }">
<span><input type="radio" id="caloriesAmount6c" name="caloriesAmount" value = "3500" checked>
    <label for="caloriesAmount6c"> 3500[cal]</label></span>
        </c:when>
        <c:otherwise>
        <span><input type="radio" id="caloriesAmount6" name="caloriesAmount" value = "3500">
    <label for="caloriesAmount6"> 3500[cal]</label></span>
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${caloriesAmount ==4000 }">
<span><input type="radio" id="caloriesAmount7c" name="caloriesAmount" value = "4000" checked>
    <label for="caloriesAmount7c"> 4000[cal]</label></span>
        </c:when>
        <c:otherwise>
        <span><input type="radio" id="caloriesAmount7" name="caloriesAmount" value = "4000">
    <label for="caloriesAmount7"> 4000[cal]</label></span>
        </c:otherwise>
    </c:choose>
    <br/>
    <div><button type="submit" name ="filter" value="true">filter</button></div>
</div>
<div>

<div>
         <label for="breakfast"> Pick breakfast </label>
         <select name="breakfast" id="breakfast">
<c:forEach items="${availableCategory}" var="meal">
         <option value="${}">${}</option>
</c:forEach>
           </select>
             </div>
<c:choose>
    <c:when test="${mealAmount ==5 }">
<div>
         <label for="secondBreakfast"> Pick second breakfast </label>
         <select name="secondBreakfast" id="secondBreakfast">
             <c:forEach items="${availableCategory}" var="meal">
         <option value="${}">${}</option>
             </c:forEach>
          </select>
             </div>
    </c:when>
    <c:otherwise> <br/>
    </c:otherwise>
</c:choose>

<div>
         <label for="dinner"> Pick dinner </label>
         <select name="dinner" id="dinner">
<c:forEach items="${availableCategory}" var="meal">
         <option value="${}">${}</option>
</c:forEach>
          </select>
             </div>

<c:choose>
    <c:when test="${mealAmount ==5 }">
<div>
         <label for="tea"> Pick tea </label>
    <c:forEach items="${availableCategory}" var="meal">
         <select name="tea" id="tea">
             <c:forEach items="${availableCategory}" var="meal">
         <option value="${}">${}</option>
             </c:forEach>
          </select>
    </c:forEach>
             </div>
    </c:when>
    <c:otherwise> <br/>
    </c:otherwise>
</c:choose>
<div>
         <label for="supper"> Pick supper </label>
         <select name="supper" id="supper">
<c:forEach items="${availableCategory}" var="meal">
         <option value="${}">${}</option>
</c:forEach>
          </select>
             </div>

<div><button type="submit" name ="filter" value="true">check</button></div>

<h3> Your meals' daily set is: </h3>
    <p> breakfast is ${}  calories: ${} [kcal]</p>
     <c:when test="${mealAmount ==5 }">
    <p> second breakfast is ${}  calories: ${} </p>>
    </c:when>
    <p> dinner is ${}  calories: ${} [kcal]</p>
    <c:when test="${mealAmount ==5 }">
    <p> breakfast is ${}  calories: ${} [kcal]</p>
    </c:when>
    <p> supper is ${}  calories: ${} [kcal]</p>

    <h3> summary calories is  ${} [kcal]</h3>

    </body>
    </html>
