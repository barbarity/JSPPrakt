package main.java.de.tum.in.dbpra.servlets;

import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.PartDAO;
import main.java.de.tum.in.dbpra.model.Part;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by barbarity on 11/06/15.
 */
public class Exercise73 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain post parameters.
        String search = request.getParameter("search");
        String column = request.getParameter("column");
        String method = request.getParameter("method");

        // Obtain DAOFactory.
        DAOFactory javabase = DAOFactory.getInstance("javabase.jdbc");
        System.out.println("DAOFactory successfully obtained: " + javabase);

        // Obtain UserDAO.
        PartDAO partDAO = javabase.getPartDAO();
        System.out.println("PartDAO successfully obtained: " + partDAO);

        // Get parts
        List<Part> parts = partDAO.search(search, column, method);

        request.setAttribute("parts", parts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Exercise73.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain DAOFactory.
        DAOFactory javabase = DAOFactory.getInstance("javabase.jdbc");
        System.out.println("DAOFactory successfully obtained: " + javabase);

        // Obtain UserDAO.
        PartDAO partDAO = javabase.getPartDAO();
        System.out.println("PartDAO successfully obtained: " + partDAO);

        // Get parts
        String sort = (String) request.getParameter("sort");

        System.out.println(sort);

        List<Part> parts = null;

        if(sort != null) {
            parts = partDAO.list(sort);
        } else {
            parts = partDAO.list();
        }

        request.setAttribute("parts", parts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Exercise73.jsp");
        dispatcher.forward(request, response);
    }
}
