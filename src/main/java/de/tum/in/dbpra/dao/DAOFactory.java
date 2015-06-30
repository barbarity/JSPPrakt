package main.java.de.tum.in.dbpra.dao;

import main.java.de.tum.in.dbpra.dao.interfaces.CustomerDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.LineitemDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.OrderDAO;
import main.java.de.tum.in.dbpra.dao.interfaces.PartDAO;
import main.java.de.tum.in.dbpra.dao.jdbc.CustomerDAOJDBC;
import main.java.de.tum.in.dbpra.dao.jdbc.LineitemDAOJDBC;
import main.java.de.tum.in.dbpra.dao.jdbc.OrderDAOJDBC;
import main.java.de.tum.in.dbpra.dao.jdbc.PartDAOJDBC;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by barbarity on 10/06/15.
 */
public abstract class DAOFactory {

    // Constants ----------------------------------------------------------------------------------

    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns a new DAOFactory instance for the given database name.
     * @param name The database name to return a new DAOFactory instance for.
     * @return A new DAOFactory instance for the given database name.
     * @throws DAOConfigurationException If the database name is null, or if the properties file is
     * missing in the classpath or cannot be loaded, or if a required property is missing in the
     * properties file, or if either the driver cannot be loaded or the datasource cannot be found.
     */
    public static DAOFactory getInstance(String name) throws DAOConfigurationException {
        if (name == null) {
            throw new DAOConfigurationException("Database name is null.");
        }

        DAOProperties properties = new DAOProperties(name);
        String url = properties.getProperty(PROPERTY_URL, true);
        String driverClassName = properties.getProperty(PROPERTY_DRIVER, false);
        String password = properties.getProperty(PROPERTY_PASSWORD, false);
        String username = properties.getProperty(PROPERTY_USERNAME, password != null);
        DAOFactory instance;

        // If driver is specified, then load it to let it register itself with DriverManager.
        if (driverClassName != null) {
            try {
                Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                throw new DAOConfigurationException(
                        "Driver class '" + driverClassName + "' is missing in classpath.", e);
            }
            instance = new DriverManagerDAOFactory(url, username, password);
        }

        // Else assume URL as DataSource URL and lookup it in the JNDI.
        else {
            DataSource dataSource;
            try {
                dataSource = (DataSource) new InitialContext().lookup(url);
            } catch (NamingException e) {
                throw new DAOConfigurationException(
                        "DataSource '" + url + "' is missing in JNDI.", e);
            }
            if (username != null) {
                instance = new DataSourceWithLoginDAOFactory(dataSource, username, password);
            } else {
                instance = new DataSourceDAOFactory(dataSource);
            }
        }

        return instance;
    }

    /**
     * Returns a connection to the database. Package private so that it can be used inside the DAO
     * package only.
     * @return A connection to the database.
     * @throws SQLException If acquiring the connection fails.
     */
    public abstract Connection getConnection() throws SQLException;

    // DAO implementation getters -----------------------------------------------------------------

    /**
     * Returns the Customer DAO associated with the current DAOFactory.
     * @return The Customer DAO associated with the current DAOFactory.
     */
    public CustomerDAO getCustomerDAO() {
        return new CustomerDAOJDBC(this);
    }

    /**
     * Returns the Order DAO associated with the current DAOFactory.
     * @return The Order DAO associated with the current DAOFactory.
     */
    public OrderDAO getOrderDAO() {
        return new OrderDAOJDBC(this);
    }

    /**
     * Returns the Part DAO associated with the current DAOFactory.
     * @return The Part DAO associated with the current DAOFactory.
     */
    public PartDAO getPartDAO() {
        return new PartDAOJDBC(this);
    }

    // You can add more DAO implementation getters here.

    public LineitemDAO getLineitemDAO() {
        return new LineitemDAOJDBC(this);
    }

}

// Default DAOFactory implementations -------------------------------------------------------------

/**
 * The DriverManager based DAOFactory.
 */
class DriverManagerDAOFactory extends DAOFactory {
    private String url;
    private String username;
    private String password;

    DriverManagerDAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}

/**
 * The DataSource based DAOFactory.
 */
class DataSourceDAOFactory extends DAOFactory {
    private DataSource dataSource;

    DataSourceDAOFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

/**
 * The DataSource-with-Login based DAOFactory.
 */
class DataSourceWithLoginDAOFactory extends DAOFactory {
    private DataSource dataSource;
    private String username;
    private String password;

    DataSourceWithLoginDAOFactory(DataSource dataSource, String username, String password) {
        this.dataSource = dataSource;
        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection(username, password);
    }
}
