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
            <td>
                <select>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                </select>
            </td>
            <td>
                <select>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                </select>
            </td>
            <td>
                <input type="text" placeholder="A000AA111">
            </td>
            <td>
                <button type="submit">Add</button>
            </td>
        </tr>
    </table>

    <input type="button" value="Sign out" name="Sign out"
           onclick="location.href = '/Parking_war_exploded/authorization-servlet'" />
</section>