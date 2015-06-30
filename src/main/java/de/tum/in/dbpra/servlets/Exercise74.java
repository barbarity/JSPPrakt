package main.java.de.tum.in.dbpra.servlets;

import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.CustomerDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.LineitemDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.OrderDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.PartDAO;
import main.java.de.tum.in.dbpra.model.Customer;
import main.java.de.tum.in.dbpra.model.Lineitem;
import main.java.de.tum.in.dbpra.model.Order;
import main.java.de.tum.in.dbpra.model.Part;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barbarity on 16/06/15.
 */
public class Exercise74 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer orderkey = Integer.parseInt(request.getParameter("orderkey"));

        // Obtain DAOFactory.
        DAOFactory javabase = DAOFactory.getInstance("javabase.jdbc");
        System.out.println("DAOFactory successfully obtained: " + javabase);

        // Obtain UserDAO.
        OrderDAO orderDAO = javabase.getOrderDAO();
        System.out.println("OrderDAO successfully obtained: " + orderDAO);

        Order order = orderDAO.find(orderkey);

        LineitemDAO lineitemDAO = javabase.getLineitemDAO();
        System.out.println("LineitemDAO successfully obtained: " + lineitemDAO);

        List<Lineitem> lineitems = lineitemDAO.list(orderkey);

        PartDAO partDAO = javabase.getPartDAO();
        System.out.println("PartDAO successfully obtained: " + partDAO);

        List<Part> parts = new ArrayList<>();

        for(Lineitem lineitem: lineitems) {
            parts.add(partDAO.find(lineitem.getPartkey()));
        }

        System.out.println(parts);

        CustomerDAO customerDAO = javabase.getCustomerDAO();

        Customer customer = customerDAO.find(order.getCustkey());

        request.setAttribute("parts", parts);
        request.setAttribute("lineitems", lineitems);
        request.setAttribute("order", order);
        request.setAttribute("customer", customer);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/Exercise74.jsp");
        dispatcher.forward(request, response);
    }
}
