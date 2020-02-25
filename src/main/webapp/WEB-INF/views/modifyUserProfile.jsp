<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-25
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ModifyUserProfile</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<form method="post" action="/userRegistration/modifyProfile">
    <div class="row">
        <div class="col-25">
            <label for="username">Username</label>
        </div>
        <div class="col-75">
            <input type="text" name="username" id="username" value="${userData.username}" readonly/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="email">Email</label>
        </div>
        <div class="col-75">
            <input type="text" name="email" id="email" value="${userData.email}"/>
        </div>
    </div>

    <div class="row">
        <div class="col-25">
            <label for="firstName">First name</label>
        </div>
        <div class="col-75">
            <input type="text" name="firstName" id="firstName" value="${userData.firstName}"/>
        </div>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="lastName">Last name</label>
        </div>
        <div class="col-75">
            <input type="text" name="lastName" id="lastName" value="${userData.lastName}"/>
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
            <input type="date" name="birthDate" id="birthDate" value="${userData.birthDate}"/>
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
    <button class="button" type="submit">Update profile</button>
    <sec:csrfInput/>
</form>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
