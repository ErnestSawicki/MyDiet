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
        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            max-width: 300px;
            margin: auto;
            text-align: center;
        }

        .title {
            color: grey;
            font-size: 18px;
        }

        a.button {
            border: none;
            outline: 0;
            display: inline-block;
            padding: 8px;
            color: white;
            background-color: #000;
            text-align: center;
            cursor: pointer;
            width: 95%;
            font-size: 18px;
        }

        a {
            text-decoration: none;
            font-size: 22px;
            color: black;
        }

        a.button:hover, a:hover {
            opacity: 0.7;
        }


    </style>
</head>
<body>
<div>
    <jsp:include page="/WEB-INF/views/fragments/header.jsp"/>
</div>
<div class="diets">
    <c:forEach items="${diets}" var="diet">
        <div class="card">
            <div class="title">
                <p>Diet name: ${diet.dietName}</p>
            </div>
            <div>
                <p>Description: ${diet.description}</p>
            </div>
            <div>
                <p>Duration: ${diet.duration}days</p>
            </div>
            <div>
                <p>Created by: ${diet.creatorUser.username}</p>
            </div>
            <a href="/diet/dietDetails?dietId=${diet.id}" class="button">Details</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
