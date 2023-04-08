package com.example.parking;

import Models.User;
import SQLQuery.LogInQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "authorizationServlet", value = "/authorization-servlet")
public class AuthorizationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("user", new User());
        request.getRequestDispatcher("authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> params = new TreeMap<>();
        var login = req.getParameter("login");
        params.put("login", login);
        params.put("password", req.getParameter("password"));
        var query = new LogInQuery();
        query.setParams(params);
        try {
            query.execute();
            if (query.getResult()) {
                var session = req.getSession();
                session.setAttribute("login", login);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/route-servlet");
    }
}