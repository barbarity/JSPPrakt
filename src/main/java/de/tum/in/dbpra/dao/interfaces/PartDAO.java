package main.java.de.tum.in.dbpra.dao.interfaces;

import main.java.de.tum.in.dbpra.dao.DAOException;
import main.java.de.tum.in.dbpra.model.Part;

import java.util.List;

/**
 * Created by barbarity on 10/06/15.
 */
public interface PartDAO {

    Part find(Integer partkey) throws DAOException;

    List<Part> list() throws DAOException;

    List<Part> list(String column) throws DAOException;

    List<Part> search(String search, String column, String method) throws IllegalArgumentException, DAOException;

    void create(Part Part) throws IllegalArgumentException, DAOException;

    void update(Part Part) throws DAOException;

    void delete(Part Part) throws DAOException;

}
