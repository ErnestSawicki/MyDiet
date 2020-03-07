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
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>UserRegistration</title>
</head>
<body>

<f:form modelAttribute="userRegistrationDTO" method="post" action="/userRegistration">
<div class="username">
    <div class="col-25">
        <f:label path="username*">Username*</f:label>
    </div>
    <div class="col-75">
        <f:input path="username" required="true"/>
    </div>
    <div class="error">
        <f:errors path="username"/>
    </div>
</div>


<div class="email">
    <div class="col-25">
        <f:label path="email">email*</f:label>
    </div>
    <div class="col-75">
        <f:input path="email" required="true" placeholder="simple@service.surfix"/>
    </div>
    <div class="error">
        <f:errors path="email"/>
    </div>
</div>

<div class="first-name">
    <div class="col-25">
        <f:label path="firstName">first name</f:label>
    </div>
    <div class="col-75">
        <f:input path="firstName"/>
    </div>
    <div class="error">
        <f:errors path="firstName"/>
    </div>
</div>

<div class="last-name">
    <div class="col-25">
        <f:label path="lastName">last name</f:label>
    </div>
    <div class="col-75">
        <f:input path="lastName"/>
    </div>
    <div class="error">
        <f:errors path="lastName"/>
    </div>
</div>

<div class="height">
    <div class="col-25">
        <f:label path="height">height</f:label>
    </div>
    <div class="col-75">
        <f:input type="number" min="0" step="1"  path="height" placeholder="xxx[cm]"/>
    </div>
    <div class="error">
        <f:errors path="height"/>
    </div>
</div>

<div class="weight">
    <div class="col-25">
        <f:label path="weight">weight</f:label>
    </div>
    <div class="col-75">
        <f:input type="number" min="0" step="0.1" path="weight" placeholder="xx.xx[kg]"/>
    </div>
    <div class="error">
        <f:errors path="weight"/>
    </div>
</div>


<div class="height">
    <div class="col-25">
        <f:label path="birthDate">birthDate</f:label>
    </div>
    <div class="col-75">
        <f:input path="birthDate" placeholder="required: DD.MM.RRRR"/>
    </div>
    <div class="error">
        <f:errors path="birthDate"/>
    </div>
</div>

<div class="sex">
    <div class="col-25">
        <f:label path="sex">sex*</f:label>
    </div>
    <div class="col-75">
        <f:select path="sex" id="selected_sex">
            <f:option value="MALE" label="male"/>
            <f:option value="FEMALE" label="female"/>
        </f:select>
    </div>
    <div class="error">
        <f:errors path="sex"/>
    </div>

    <div class="password-field">
        <div class="col-25">
            <f:label path="password">password*</f:label>
        </div>
        <div class="col-75">
            <f:password path="password" required="true"/>
        </div>
        <div class="error">
            <f:errors path="password"/>
        </div>
    </div>

    <div class="password-field">
        <div class="col-25">
            <f:label path="confirmPassword">confirm password*</f:label>
        </div>
        <div class="col-75">
            <f:password path="confirmPassword" required="true"/>
        </div>
        <div class="error">
            <f:errors path="confirmPassword"/>
        </div>
    </div>

    <button class="button" type="submit">Login</button>
        <%--        <sec:csrfInput/>--%>

    </f:form>
</body>
</html>

