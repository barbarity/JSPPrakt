package main.java.de.tum.in.dbpra.dao.jdbc;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.dao.DAOFactory;
import main.java.de.tum.in.dbpra.dao.interfaces.LineitemDAO;
import main.java.de.tum.in.dbpra.model.Lineitem;
import main.java.de.tum.in.dbpra.model.Order;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
public class LineitemDAOJDBC implements LineitemDAO {

    private static final String TABLE_NAME = "Lineitem";
    private static final String DEFAULT_COLUMNS = "orderkey, partkey, suppkey, linenumber, quantity, extendedprice, discount, tax";

    private static final String SQL_FIND_BY_ORDERKEY =
            "SELECT " + DEFAULT_COLUMNS + " FROM " + TABLE_NAME + " WHERE orderkey = ?";

    private DAOFactory daoFactory;

    public LineitemDAOJDBC(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Lineitem> list(Integer orderkey) throws DAOException {
        return list(SQL_FIND_BY_ORDERKEY, orderkey);
    }

    public List<Lineitem> list(String sql, Object... values) throws DAOException {
        List<Lineitem> lineitems = new ArrayList<Lineitem>();

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement statement = prepareStatement(connection, sql, false, values);
                ResultSet resultSet = statement.executeQuery()
        ){
            while (resultSet.next()) {
                lineitems.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return lineitems;
    }


    private static Lineitem map(ResultSet resultSet) throws SQLException {
        Lineitem lineitem = new Lineitem();
        lineitem.setOrderkey(resultSet.getInt("orderkey"));
        lineitem.setPartkey(resultSet.getInt("partkey"));
        lineitem.setSuppkey(resultSet.getInt("suppkey"));
        lineitem.setLinenumber(resultSet.getInt("linenumber"));
        lineitem.setQuantity(resultSet.getInt("quantity"));
        lineitem.setExtendedprice(resultSet.getBigDecimal("extendedprice"));
        lineitem.setDiscount(resultSet.getBigDecimal("discount"));
        lineitem.setTax(resultSet.getBigDecimal("tax"));
        return lineitem;
    }
}
