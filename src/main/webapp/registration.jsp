<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript" src="JS/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="JS/authorization.js"></script>
<link href="styles/style.css" rel="stylesheet" type="text/css">
<section>
    <form method="post" action="registration-servlet?action=submit">
        <dl>
            <dt>Name: </dt>
            <dd> <input type="text" id="userName"></dd>
        </dl>
        <dl>
            <dt>Card Number: </dt>
            <dd> <input type="text" id="cardNumber"></dd>
        </dl>
        <dl>
            <dt>Login: </dt>
            <dd> <input type="email" id="login"></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd> <input type="password" id="password"></dd>
        </dl>
        <h5 id="error-text"></h5>
        <input type="button" onclick="signUp()" value="Create"/>
    </form>
    <input type="button" value="Sign in" name="Sign in"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
</section>