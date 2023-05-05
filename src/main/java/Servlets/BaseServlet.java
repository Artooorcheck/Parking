package Servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Properties;

public class BaseServlet extends HttpServlet {
    protected Properties getProperties(ServletConfig config) {
        var properties = new Properties();
        try {
            properties.load(config.getServletContext().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
