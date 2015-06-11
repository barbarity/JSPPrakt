package main.java.de.tum.in.dbpra.servlets;

import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.CustomerDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.OrderDAO;
import main.java.de.tum.in.dbpra.model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public class Exercise72 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain DAOFactory.
        DAOFactory javabase = DAOFactory.getInstance("javabase.jdbc");
        System.out.println("DAOFactory successfully obtained: " + javabase);

        // Obtain UserDAO.
        OrderDAO orderDAO = javabase.getOrderDAO();
        System.out.println("UserDAO successfully obtained: " + orderDAO);

        // Get orders
        List<Order> ordersOK = orderDAO.listByOrderStatus("ok");
        List<Order> ordersNO = orderDAO.listByOrderStatus("no");

        // Pass them to the request
        request.setAttribute("ordersOK", ordersOK);
        request.setAttribute("ordersNO", ordersNO);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Exercise72.jsp");
        dispatcher.forward(request, response);
    }
}
