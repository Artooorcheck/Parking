package com.example.parking;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "routeServlet", value = "/route-servlet")
public class RouteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var session = request.getSession();
        var login = session.getAttribute("login");
        var page = "/authorization-servlet";
        if (login != null) {
            page = "/userPlaces-servlet";
        }
        response.sendRedirect(request.getContextPath() + page);
    }
}