package mg.sandratra.mattrex.controllers;

import mg.sandratra.mattrex.models.entities.Dimension;
import mg.sandratra.mattrex.models.entities.Foam;
import mg.sandratra.mattrex.models.service.FoamService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/foam")
public class FoamController extends HttpServlet {

    private FoamService foamService;

    @Override
    public void init() {
        foamService = new FoamService();
    }

    // Handle CREATE and UPDATE
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            double width = Double.parseDouble(request.getParameter("width"));
            double height = Double.parseDouble(request.getParameter("height"));
            double depth = Double.parseDouble(request.getParameter("depth"));
            double costPrice = Double.parseDouble(request.getParameter("cost_price"));

            // Create Dimension and Foam objects with provided values and default values
            Dimension dimension = new Dimension(0, width, height, depth); // Dimension ID is set to 0 by default
            Foam foam = new Foam(0, dimension, null, costPrice, 0.0, new Timestamp(System.currentTimeMillis()), true);

            if ("update".equalsIgnoreCase(action)) {
                int foamId = Integer.parseInt(request.getParameter("id"));
                foam.setId(foamId);
                foamService.updateFoam(foam);
                response.sendRedirect("foam?action=view&id=" + foamId);
            } else {
                // Default to "create" if no valid action is provided
                int createdFoamId = foamService.createFoam(foam);
                response.sendRedirect("foam?action=view&id=" + createdFoamId);
            }

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to process request.");
        }
    }

    // Handle READ and DELETE
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            if ("view".equalsIgnoreCase(action)) {
                int foamId = Integer.parseInt(request.getParameter("id"));
                Foam foam = foamService.getFoamById(foamId);
                request.setAttribute("foam", foam);
                request.getRequestDispatcher("/WEB-INF/views/foam_view.jsp").forward(request, response);

            } else if ("delete".equalsIgnoreCase(action)) {
                int foamId = Integer.parseInt(request.getParameter("id"));
                foamService.deleteFoam(foamId);
                response.sendRedirect("foam?action=list");

            } else if ("list".equalsIgnoreCase(action)) {
                List<Foam> foams = foamService.getAllFoams();
                request.setAttribute("foams", foams);
                request.getRequestDispatcher("/WEB-INF/views/foam_list.jsp").forward(request, response);

            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }

        } catch (SQLException | ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Failed to process request.");
        }
    }
}
