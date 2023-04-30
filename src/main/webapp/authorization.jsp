<%@ page import="Models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="JS/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="JS/authorization.js"></script>
<link href="styles/style.css" rel="stylesheet" type="text/css">
<section>
    <jsp:useBean id="user" type="Models.User" scope="request"/>
    <form method="post" action="authorization-servlet?action=submit">
        <dl>
            <dt>Login: </dt>
            <dd> <input type="text" name="login" id="login"></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd> <input type="password" name="password" id="password"></dd>
        </dl>
        <h5 id="error-text"></h5>
        <input type="button" onclick="signIn()" value="Log in"/>
    </form>
    <input type="button" value="Sign up" name="Sign up"
           onclick="location.href = '/Parking_war_exploded/registration-servlet'" />
</section>