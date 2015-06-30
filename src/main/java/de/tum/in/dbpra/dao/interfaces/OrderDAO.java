package main.java.de.tum.in.dbpra.dao.interfaces;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.model.Customer;
import main.java.de.tum.in.dbpra.model.Order;

import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public interface OrderDAO {

    Order find(Integer orderkey) throws DAOException;

    List<Order> list() throws DAOException;

    List<Order> listByOrderStatus(String orderstatus) throws DAOException;

    void create(Order order) throws IllegalArgumentException, DAOException;

    void update(Order order) throws DAOException;

    void delete(Order order) throws DAOException;

}
