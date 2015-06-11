package main.java.de.tum.in.dbpra.dao.interfaces;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.model.Customer;

import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public interface CustomerDAO {

    public Customer find(Integer custkey) throws DAOException;

    public List<Customer> list() throws DAOException;

    public void create(Customer customer) throws IllegalArgumentException, DAOException;

    public void update(Customer customer) throws DAOException;

    public void delete(Customer customer) throws DAOException;

}
