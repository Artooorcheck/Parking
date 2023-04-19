package Servlets;

import SQLQuery.CRUDTemplates.SetDataQuery;
import SQLQuery.LeaveCarQuery;
import SQLQuery.RemoveCarQuery;
import SQLQuery.UserPlacesQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

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
        params.put("Login", login);
        query.setParams(params);

        try {
            query.execute();
            var result = query.getResult();
            var json = new JSONArray(result);
            var writer = response.getWriter();
            writer.print(json);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var params = new HashMap<String, Object>();
        var placeId = req.getParameter("placeId");
        var session = req.getSession();
        var login = session.getAttribute("login");

        var type = req.getParameter("type");
        params.put("Place_id", placeId);
        params.put("Login", login);
        SetDataQuery query = null;
        if (type.equals("add")) {
            var carId = req.getParameter("carId");
            query = new LeaveCarQuery();
            params.put("Car_id", carId);
        } else if (type.equals("delete")) {
            query = new RemoveCarQuery();
        }
        query.setParams(params);
        try {
            query.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}