package main.java.de.tum.in.dbpra.dao.interfaces;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.model.Customer;

import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public interface CustomerDAO {

    Customer find(Integer custkey) throws DAOException;

    List<Customer> list() throws DAOException;

    void create(Customer customer) throws IllegalArgumentException, DAOException;

    void update(Customer customer) throws DAOException;

    void delete(Customer customer) throws DAOException;

}
