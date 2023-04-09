package Servlets;

import Models.User;
import SQLQuery.SignUpQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@WebServlet(name = "registrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("user", new User());
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> params = new TreeMap<>();
        params.put("userName", req.getParameter("userName"));
        params.put("cardNumber", req.getParameter("cardNumber"));
        params.put("login", req.getParameter("login"));
        params.put("password", req.getParameter("password"));
        var query = new SignUpQuery();
        query.setParams(params);
        query.execute();
        resp.sendRedirect(req.getContextPath() + "/route-servlet");
    }
}