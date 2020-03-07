<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-03-07
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>AllDiets</title>
    <style>
        .tile {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
        }

        .tile:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }
        .container {
            padding: 2px 16px;
        }

    </style>
</head>
<body>
<div>
    <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
</div>
<p1>Here all diets</p1>
<div class="diets">
    <c:forEach items="${diets}" var="diet">
        <div class="tile">
            <div class="container">
                <div class="name">
                    <p1>Diet name:</p1>
                    <p1>${diet.dietName}</p1>
                </div>
                <div>
                    <p1>Description</p1>
                    <p1>${diet.description}</p1>
                </div>
                <div>
                    <p1>Duration</p1>
                    <p1>${diet.duration}</p1>
                </div>
                <div>
                    <p1>Created by</p1>
                    <p1>${diet.creatorUser.username}</p1>
                </div>
                <div>
                    <a href="/diet/dietDetails?dietId=${diet.id}">Details</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>
