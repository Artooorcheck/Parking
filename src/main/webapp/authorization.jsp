<%@ page import="Models.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<section>
    <jsp:useBean id="user" type="Models.User" scope="request"/>
    <form method="post" action="authorization-servlet?action=submit">
        <dl>
            <dt>Login: </dt>
            <dd> <input type="email" name="login" value="${user.login}" placeholder="${user.login}"></dd>
        </dl>
        <dl>
            <dt>Password: </dt>
            <dd> <input type="password" name="password" value="${user.password}" placeholder="${user.password}"></dd>
        </dl>
        <button type="submit">Log in</button>
    </form>
    <input type="button" value="Sign up" name="Sign up"
           onclick="location.href = '/Parking_war_exploded/registration-servlet'" />
</section>