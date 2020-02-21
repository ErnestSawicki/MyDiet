<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-15
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Header</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <%--<link rel="stylesheet" type="text/css" href="/css/styleHeader.css">--%>
    <style>
        *{
            margin: 0;
            padding: 0;
            list-style: none;
            text-decoration: none;
        }

        .header{
            width: 100%;
            height: 80px;
            display: block;
            background-color: darkgray;
        }
        .inner_footer{
            width: 80%;
            height: 100%;
            display: block;
            margin: 0 auto;
        }

        .logo_container{
            height: 100%;
            display: table;
            float: left;
        }

        .logo_container h1{
            color: green;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: 'Montserrat';
            font-size: 32px;
        }

        .navigation{
            float: right;
            height: 100%;
        }

        .navigation a{
            height: 100%;
            display: table;
            float: left;
            padding: 0px 20px;
        }

        .navigation a:last-child{
            padding-right: 0;
        }

        .navigation a li{
            display: table-cell;
            vertical-align: middle;
            height: 100%;
            color: white;
            font-family: 'Montserrat';
            font-size: 16px;
        }

        .myButton{
            border: none;
            background-color: green;
            border-radius: 15px;
            color: white;
            padding: 14px 28px;
            font-size: 16px;
            font-family: 'Montserrat';
            cursor: pointer;
        }

        .myButton:hover{background: #eee;}

    </style>
</head>
<body>
<div class="header">
    <div class="inner_header">
        <div class="logo_container">
            <h1>MyDiet</h1>
        </div>
        <ul class="navigation">
            <a>
                <li><sec:authorize access="isAuthenticated()">
                    <form method="get" action="/">
                        <button class="myButton" type="submit">Home</button>
                        <sec:csrfInput/>
                    </form>
                </sec:authorize></li>
            </a>
            <a>
                <li><sec:authorize access="!isAuthenticated()">
                    <form method="get" action="/login">
                        <button class="myButton" type="submit">Login</button>
                        <sec:csrfInput/>
                    </form>
                </sec:authorize></li>
            </a>
            <a>
                <li><sec:authorize access="!isAuthenticated()">
                    <form method="get" action="/userRegistration">
                        <button class="myButton" type="submit">Register</button>
                        <sec:csrfInput/>
                    </form>
                </sec:authorize></li>
            </a>
            <a>
                <li><sec:authorize access="isAuthenticated()">
                    <form method="get" action="/logout">
                        <button class="myButton" type="submit">Logout</button>
                        <sec:csrfInput/>
                    </form>
                </sec:authorize></li>
            </a>
        </ul>
    </div>
</div>

</body>
</html>
