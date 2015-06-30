package main.java.de.tum.in.dbpra.dao.interfaces;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.model.Customer;
import main.java.de.tum.in.dbpra.model.Lineitem;

import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public interface LineitemDAO {

    List<Lineitem> list(Integer orderkey) throws DAOException;

}
