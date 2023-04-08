package com.example.parking;

import SQLQuery.UserPlacesQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "userPlacesServlet", value = "/userPlaces-servlet")
public class UserPlacesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var session = request.getSession();
        var login = session.getAttribute("login");
        var query = new UserPlacesQuery();
        var params = new HashMap<String, Object>();
        params.put("login", login);
        query.setParams(params);

        try {
            query.execute();
            var result = query.getResult();
            request.setAttribute("busyPlaces", result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

    }
}