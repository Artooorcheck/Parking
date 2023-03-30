package com.example.parking;

import java.io.*;

import Models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

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
        // Hello
        request.getRequestDispatcher("authorization.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("userName"));
        System.out.println(req.getParameter("login"));
        System.out.println(req.getParameter("password"));
    }

    public void destroy() {
    }
}