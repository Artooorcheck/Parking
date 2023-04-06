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
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("user", new User());
        request.getRequestDispatcher("authorization.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> params = new TreeMap<>();
        params.put("login", req.getParameter("login"));
        params.put("password", req.getParameter("password"));
        var query = new LogInQuery();
        query.setParams(params);
        query.execute();
        try {
            System.out.println(query.getResult());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}