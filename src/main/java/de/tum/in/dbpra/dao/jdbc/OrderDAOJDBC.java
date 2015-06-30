package main.java.de.tum.in.dbpra.dao.jdbc;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.OrderDAO;
import main.java.de.tum.in.dbpra.model.Customer;
import main.java.de.tum.in.dbpra.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.de.tum.in.dbpra.dao.DAOUtil.prepareStatement;

/**
 * Created by barbarity on 11/06/15.
 */
public class OrderDAOJDBC implements OrderDAO {

    private static final String TABLE_NAME = "Orders";
    private static final String DEFAULT_COLUMNS = "orderkey, custkey, orderstatus, totalprice, orderdate";

    private static final String SQL_FIND_BY_ORDERKEY =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE orderkey = ?";
    private static final String SQL_LIST =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME;
    private static final String SQL_LIST_BY_ORDERSTATUS =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE orderstatus = ?";

    private DAOFactory daoFactory;

    public OrderDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Order find(Integer orderkey) throws DAOException {
        return find(SQL_FIND_BY_ORDERKEY, orderkey);
    }

    private Order find(String sql, Object... values) {
        Order order = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                order = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return order;
    }

    @Override
    public List<Order> list() throws DAOException {
        List<Order> orders = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                orders.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return orders;
    }

    @Override
    public List<Order> listByOrderStatus(String orderstatus) throws DAOException {
        return list(SQL_LIST_BY_ORDERSTATUS, orderstatus);
    }

    public List<Order> list(String sql, Object... values) throws DAOException {
        List<Order> orders = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                orders.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return orders;
    }

    @Override
    public void create(Order order) throws IllegalArgumentException, DAOException {

    }

    @Override
    public void update(Order order) throws DAOException {

    }

    @Override
    public void delete(Order order) throws DAOException {

    }

    private static Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setOrderkey(resultSet.getInt("orderkey"));
        order.setCustkey(resultSet.getInt("custkey"));
        order.setOrderstatus(resultSet.getString("orderstatus"));
        order.setTotalprice(resultSet.getBigDecimal("totalprice"));
        order.setOrderdate(resultSet.getDate("orderdate"));
        return order;
    }
}
