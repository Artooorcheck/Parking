<%@ page import="Models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<section>
    <jsp:useBean id="user" type="Models.User" scope="request"/>
    <form method="post" action="registration-servlet?action=submit">
        <dl>
            <dt>Name: </dt>
            <dd> <input type="text" name="userName" value="${user.name}" placeholder="${user.name}"></dd>
        </dl>
        <dl>
            <dt>Card Number: </dt>
            <dd> <input type="text" name="cardNumber" value="${user.card}" placeholder="${user.card}"></dd>
        </dl>
        <dl>
            <dt>Login: </dt>
            <dd> <input type="email" name="login" value="${user.login}" placeholder="${user.login}"></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd> <input type="password" name="password" value="${user.password}" placeholder="${user.password}"></dd>
        </dl>
        <button type="submit">Create</button>
    </form>
    <input type="button" value="Sign in" name="Sign in"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
</section>