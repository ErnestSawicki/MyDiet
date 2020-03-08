<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-29
  Time: 09:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>DietAssign</title>
</head>
<body>
<div>
    <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
</div>
<form action="/diet/assignDiet" method="post">
    <select name="dietId">
        <c:forEach items="${diets}" var="diet">
            <option value="${diet.id}">${diet.dietName}</option>
        </c:forEach>
    </select>
    <div>
        <label for="startDate">Diet start date: </label>
    </div>
    <div>
        <input id="startDate" name="startDate" type="date"/>
    </div>
    <button type="submit" >Get date</button>
    <sec:csrfInput/>
</form>
</body>
</html>
