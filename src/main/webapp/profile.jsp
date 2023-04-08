<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section>
    <table>
        <tr>
            <th>Address</th>
            <th>Place</th>
            <th>Car Identifier</th>
        </tr>
        <c:forEach items="${busyPlaces}" var="item">
            <tr>
                <td id="viewA<c:out value="${item.placeId}" />" >
                    <c:out value="${item.parkAddress}" />
                </td>
                <td id="viewB<c:out value="${item.placeId}" />" >
                    <c:out value="${item.placeNumber}" />
                </td>
                <td id="viewC<c:out value="${item.placeId}" />" >
                    <c:out value="${item.carId}" />
                </td>
            </tr>
        </c:forEach>
    </table>

    <input type="button" value="Sign in" name="Sign in"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
</section>