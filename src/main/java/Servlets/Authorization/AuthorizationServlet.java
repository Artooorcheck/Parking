package Servlets.Authorization;

import Models.User;
import Services.AuthorizationService;
import Servlets.BaseServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "authorizationServlet", value = "/authorization-servlet")
public class AuthorizationServlet extends BaseServlet {

    private AuthorizationService service;

    @Override
    public  void init(ServletConfig config) {
        service = new AuthorizationService(getProperties(config));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println(request.getServletContext().getRealPath("/config.properties"));
        request.getSession().removeAttribute("login");
        response.setContentType("text/html");
        request.setAttribute("user", new User());
        request.getRequestDispatcher("authorization.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var login = req.getParameter("login");
        var password = req.getParameter("password");
        try {
            if (service.loginIsExist(login, password)) {
                var session = req.getSession();
                session.setAttribute("login", login);
            }
            else {
                resp.getWriter().print("login or password isn't correct");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //resp.sendRedirect(req.getContextPath() + "/route-servlet");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var login = session.getAttribute("login");
        if(login == null || login.equals("")) {
            return;
        }
        try {
            service.deleteUser((String) login);
            session.removeAttribute("login");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}