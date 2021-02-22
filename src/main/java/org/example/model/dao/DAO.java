package org.example.model.dao;

import org.example.model.dao.exception.DAOException;

import java.sql.SQLException;

public interface DAO<T> {
    T read() throws DAOException, SQLException;
}