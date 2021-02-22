package org.example.model.dao;

import org.example.model.dao.exception.DAOException;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface EnrolleeInfoDAO<T> {
    List<T> read() throws DAOException, SQLException;
    void delete(int index) throws DAOException, SQLException;
    void update(int index, T t) throws DAOException, SQLException;
    void create(T t) throws DAOException, SQLException;
}
