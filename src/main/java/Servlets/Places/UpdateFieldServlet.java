package Servlets.Places;

import Services.CarService;
import Servlets.BaseServlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateFieldServlet", value = "/updateField-servlet")
public class UpdateFieldServlet extends BaseServlet {

    private CarService service;

    @Override
    public  void init(ServletConfig config) {
        service = new CarService(getProperties(config));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var name = request.getParameter("fieldName");
        JSONArray json = null;
        try {
            var writer = response.getWriter();
            if (name.equals("getPlaces")) {
                var parkId = request.getParameter("parkId");
                writer.print(new JSONArray(service.getFreePlaces(parkId)));
            } else if (name.equals("getParks")) {
                writer.print(new JSONArray(service.getAvailableParks()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
