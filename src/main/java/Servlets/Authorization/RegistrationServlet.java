package Servlets.Authorization;

import Models.User;
import SQLQuery.SignUpQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.sql.SQLException;
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
        params.put("Name", req.getParameter("userName"));
        params.put("Card_number", req.getParameter("cardNumber"));
        params.put("Login", req.getParameter("login"));
        params.put("Password", req.getParameter("password"));
        var query = new SignUpQuery(req);
        try {
            query.setParams(params);
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch ( ValidationException e) {
            resp.getWriter().print("invalid field");
        }
    }
}