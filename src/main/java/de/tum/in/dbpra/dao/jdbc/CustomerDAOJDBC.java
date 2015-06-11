package main.java.de.tum.in.dbpra.dao.jdbc;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.CustomerDAO;
import main.java.de.tum.in.dbpra.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.de.tum.in.dbpra.dao.DAOUtil.*;

/**
 * Created by barbarity on 10/06/15.
 */
public class CustomerDAOJDBC implements CustomerDAO {

    private static final String TABLE_NAME = "Customer";
    private static final String DEFAULT_COLUMNS = "custkey, name, address, nationkey, phone, fax, acctbal";

    private static final String SQL_FIND_BY_CUSTKEY =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + "WHERE custkey = ?";
    private static final String SQL_LIST =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME;
    private static final String SQL_INSERT =
            "INSERT INTO " + TABLE_NAME + " (" + DEFAULT_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE " + TABLE_NAME + " SET name = ?, address = ?, nationkey = ?, phone = ?, fax = ?, acctbal = ? WHERE custkey = ?";

    private DAOFactory daoFactory;

    public CustomerDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Customer find(Integer custkey) throws DAOException {
        return find(SQL_FIND_BY_CUSTKEY, custkey);
    }

    private Customer find(String sql, Object... values) {
        Customer customer = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery();
        ) {
            if (resultSet.next()) {
                customer = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return customer;
    }

    public List<Customer> list() throws DAOException {
        List<Customer> customers = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST);
                ResultSet resultSet = statement.executeQuery();
        ) {
            while (resultSet.next()) {
                customers.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return customers;
    }

    public void create(Customer customer) throws IllegalArgumentException, DAOException {
        // Check customer has all the required properties.
        if (customer.getCustkey() == null) {
            throw new IllegalArgumentException("Customer needs a Customer id.");
        }
        if (customer.getName() == null) {
            throw new IllegalArgumentException("Customer needs a name.");
        }
        if (customer.getAddress() == null) {
            throw new IllegalArgumentException("Customer needs an address.");
        }
        if (customer.getNationkey() == null) {
            throw new IllegalArgumentException("Customer needs a Nation id.");
        }

        Object[] values = {
                customer.getCustkey(),
                customer.getName(),
                customer.getAddress(),
                customer.getNationkey(),
                customer.getPhone(),
                customer.getFax(),
                customer.getAcctbal()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_INSERT, true, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating user failed, no rows affected.");
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void update(Customer customer) throws DAOException {
        if (customer.getCustkey() == null) {
            throw new IllegalArgumentException("Customer is not created yet, the Customer id is null.");
        }

        Object[] values = {
                customer.getName(),
                customer.getAddress(),
                customer.getNationkey(),
                customer.getPhone(),
                customer.getFax(),
                customer.getAcctbal(),
                customer.getCustkey()
        };

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, SQL_UPDATE, false, values);
        ) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void delete(Customer customer) throws DAOException {

    }

    private static Customer map(ResultSet resultSet) throws SQLException {
        Customer customer = new Customer();
        customer.setCustkey(resultSet.getInt("custkey"));
        customer.setName(resultSet.getString("name"));
        customer.setAddress(resultSet.getString("address"));
        customer.setNationkey(resultSet.getInt("nationkey"));
        customer.setPhone(resultSet.getString("phone"));
        customer.setFax(resultSet.getString("fax"));
        customer.setAcctbal(resultSet.getBigDecimal("acctbal"));
        return customer;
    }
}
