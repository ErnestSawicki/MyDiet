<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-15
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Footer</title>
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <style>
        *{
            margin: 0;
            padding: 0;
            list-style: none;
            text-decoration: none;
        }

        .footer{
            width: 100%;
            height: 80px;
            display: block;
            background-color: darkgray;
            position: absolute;
            bottom: 0;

        }
        .inner_footer{
            width: 80%;
            height: 100%;
            display: block;
            margin: 0 auto;
        }

        .copyright_container{
            height: 100%;
            display: table;
            float: left;
        }

        .copyright_container h1{
            color: white;
            height: 100%;
            display: table-cell;
            vertical-align: middle;
            font-family: 'Montserrat';
            font-size: 16px;
        }

        .copyright_container h1 scope{
            font-size: 22px;
        }

    </style>
</head>
<body>
<div class="footer">
    <div class="inner_footer">
        <div class="copyright_container">
            <h1>Copyright&copy;2020<scope> Ernest Sawicki&Arkadiusz Przytula</scope> </h1>
        </div>
        <div class="MyDietLogo">
        </div>
    </div>
</div>
</body>
</html>
