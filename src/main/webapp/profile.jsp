<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="JS/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="JS/park-car.js"></script>
<section>
    <table id="car_list">
        <tr>
            <th>Address</th>
            <th>Place</th>
            <th>Car Identifier</th>
        </tr>
        <c:forEach items="${busyPlaces}" var="item">
            <tr class="row">
                <td>
                    <c:out value="${item.parkAddress}" />
                </td>
                <td>
                    <c:out value="${item.placeNumber}" />
                </td>
                <td>
                    <c:out value="${item.carId}" />
                </td>
                <td>
                    <button type="submit" id="<c:out value="${item.placeId}" />">Remove</button>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td><select id="park_id" onchange="getPlaces()"></select></td>
            <td><select id="park_place"></select></td>
            <td><input id="car_id" type="text" placeholder="A000AA111"></td>
            <td><button type="submit" id="set_button" onclick="leaveCar()">Add</button></td>
        </tr>
    </table>

    <input type="button" value="Sign out" name="Sign out"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
</section>