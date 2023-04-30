package Servlets.Places;

import Models.Park;
import Models.ParkPlace;
import SQLQuery.AvailableParkQuery;
import SQLQuery.CRUDTemplates.GetDataQuery;
import SQLQuery.FreePlacesQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.ValidationException;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "updateFieldServlet", value = "/updateField-servlet")
public class UpdateFieldServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var name = request.getParameter("fieldName");
        System.out.println(name);
        GetDataQuery query = null;
        if (name.equals("getPlaces")) {
            query = new FreePlacesQuery(request);
            var params = new HashMap<String, Object>();
            var parkId = request.getParameter("parkId");
            params.put("Park_id", parkId);
            try {
                query.setParams(params);
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
        if (name.equals("getParks")) {
            query = new AvailableParkQuery(request);
        }
        loadData(query, response.getWriter());
    }

    private void loadData(GetDataQuery query, PrintWriter writer) {
        try {
            query.execute();
            var result = query.getResult();
            var json = ToJson(result);
            System.out.println(json);
            writer.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONArray ToJson (Object collection) throws JSONException, ClassCastException {
        try {
            return new JSONArray((ArrayList<Park>)collection);
        } catch (ClassCastException e) {}
        return new JSONArray((ArrayList<ParkPlace>)collection);
    }
}
