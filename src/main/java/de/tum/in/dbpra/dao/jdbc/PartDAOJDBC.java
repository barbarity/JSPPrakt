package main.java.de.tum.in.dbpra.dao.jdbc;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.PartDAO;
import main.java.de.tum.in.dbpra.model.Part;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.de.tum.in.dbpra.dao.DAOUtil.prepareStatement;

/**
 * Created by barbarity on 10/06/15.
 */
public class PartDAOJDBC implements PartDAO {

    private static final String TABLE_NAME = "Part";
    private static final String DEFAULT_COLUMNS = "partkey, name, type, size, container, retailprice";

    private static final String SQL_FIND_BY_CUSTKEY =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE partkey = ?";
    private static final String SQL_LIST =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME;
    private static final String SQL_INSERT =
            "INSERT INTO " + TABLE_NAME + " (" + DEFAULT_COLUMNS + ") VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE " + TABLE_NAME + " SET name = ?, type = ?, size = ?, container = ?, retailprice = ? WHERE partkey = ?";

    private DAOFactory daoFactory;

    public PartDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Part find(Integer custkey) throws DAOException {
        return find(SQL_FIND_BY_CUSTKEY, custkey);
    }

    private Part find(String sql, Object... values) {
        Part part = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                part = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return part;
    }

    public List<Part> list(String column) throws DAOException {
        List<Part> parts = new ArrayList<Part>();

        String sql = SQL_LIST + " ORDER BY " + column;

        System.out.println(sql);

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                parts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return parts;
    }

    public List<Part> list() throws DAOException {
        List<Part> parts = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_LIST);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                parts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return parts;
    }

    public List<Part> search(String search, String column, String method) throws DAOException {
        String sql = null;

        System.out.println(method);

        if (method.equals("like")) {
                sql = "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE " + column + "::text LIKE '%"+ search +"%' ";
        } else {
            if (column.equals("name") || column.equals("type")) {
                sql = "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE " + column + " = '" + search + "'";
            } else {
                sql = "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE " + column + " = " + search;
            }
        }

        System.out.print(sql);

        List<Part> parts = new ArrayList<>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                parts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return parts;
    }

    public void create(Part part) throws IllegalArgumentException, DAOException {
        // TODO: Implement create part
    }

    public void update(Part part) throws DAOException {
        //TODO: Implement update
    }

    public void delete(Part part) throws DAOException {

    }

    private static Part map(ResultSet resultSet) throws SQLException {
        Part part = new Part();
        part.setPartkey(resultSet.getInt("partkey"));
        part.setName(resultSet.getString("name"));
        part.setType(resultSet.getString("type"));
        part.setSize(resultSet.getInt("size"));
        part.setContainer(resultSet.getInt("container"));
        part.setRetailprice(resultSet.getBigDecimal("retailprice"));
        return part;
    }
}
