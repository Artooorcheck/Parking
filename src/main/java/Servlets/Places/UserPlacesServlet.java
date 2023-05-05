package Servlets.Places;

import Services.CarService;
import Servlets.BaseServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.ValidationException;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "userPlacesServlet", value = "/userPlaces-servlet")
public class UserPlacesServlet extends BaseServlet {

    private CarService service;

    @Override
    public  void init(ServletConfig config) {
        service = new CarService(getProperties(config));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var session = request.getSession();
        var login = session.getAttribute("login");

        try {
            var json = new JSONArray(service.getUserCars((String) login));
            var writer = response.getWriter();
            writer.print(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var login = session.getAttribute("login");
        var placeId = req.getParameter("placeId");
        var carId = req.getParameter("carId");

        try {
            service.leaveCar(placeId, (String) login, carId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            resp.getWriter().print("Invalid car id");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var params = new HashMap<String, Object>();

        var placeId = req.getParameter("placeId");
        var session = req.getSession();
        var login = session.getAttribute("login");

        params.put("Place_id", placeId);
        params.put("Login", login);

        try {
            service.removeCar(placeId,(String) login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}