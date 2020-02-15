<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-15
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>UserRegistration</title>
</head>
<body>
<form method="post" action="/userRegistration">
    <div class="row">
        <div class="col-25">
            <label for="username">Username</label>
        </div>
        <div class="col-75">
            <input type="text" name="username" id="username"/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="email">Email</label>
        </div>
        <div class="col-75">
            <input type="text" name="email" id="email"/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="firstName">First name</label>
        </div>
        <div class="col-75">
            <input type="text" name="firstName" id="firstName"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="lastName">Last name</label>
        </div>
        <div class="col-75">
            <input type="text" name="lastName" id="lastName"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="height">Height</label>
        </div>
        <div class="col-75">
            <input type="number" name="height" id="height"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="weight">Weight</label>
        </div>
        <div class="col-75">
            <input type="number" name="weight" id="weight"/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="birthDate">Birth date</label>
        </div>
        <div class="col-75">
            <input type="date" name="birthDate" id="birthDate"/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="sex">Sex</label>
        </div>
        <div>
            <select name="sex" id="sex">
                <option value="MALE">Male</option>
                <option value="FEMALE">Female</option>
            </select>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="password">Password</label>
        </div>
        <input type="text" name="password" id="password"/>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="confirmPassword">Confirm password</label>
        </div>
        <input type="text" name="confirmPassword" id="confirmPassword"/>
    </div>
    <button class="button" type="submit">Login</button>
    <sec:csrfInput/>
</form>
</body>
</html>
