<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="JS/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="JS/park-car.js"></script>
<link href="styles/style.css" rel="stylesheet" type="text/css">
<section>
    <table id="car_list">
    </table>
    <h5 id="error-text"></h5>

    <input type="button" value="Sign out" name="Sign out"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
    <input type="button" value="Delete profile" name="Delete profile"
           onclick="deleteUser()" />
</section>