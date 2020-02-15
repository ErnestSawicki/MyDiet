
?<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 2020-02-15
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>LoginPage</title>
</head>
<body>
<form method="post" action="/login">
    <div class="row">
        <div class="col-25">
            <label for="username">Username</label>
        </div>
        <input type="text" name="username" id="username"/>
    </div>
    <div class="row">
        <div class="col-25">
            <label for="password">Password</label>
        </div>
        <input type="text" name="password" id="password"/>
    </div>
    <button class="button" type="submit">Login</button>>
    <sec:csrfInput/>
</form>

</body>
</html>
