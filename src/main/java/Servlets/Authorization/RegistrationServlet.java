package Servlets.Authorization;

import Models.User;
import Services.AuthorizationService;
import Servlets.BaseServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.ValidationException;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registrationServlet", value = "/registration-servlet")
public class RegistrationServlet extends BaseServlet {

    private AuthorizationService service;

    @Override
    public  void init(ServletConfig config) {
        service = new AuthorizationService(getProperties(config));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.setAttribute("user", new User());
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("userName");
        String cardNumber = req.getParameter("cardNumber");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            service.createUser(login, password, cardNumber, name);
        } catch (ValidationException e) {
            e.printStackTrace();
            resp.getWriter().print("invalid field");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().print("login already exist");
        }
    }
}