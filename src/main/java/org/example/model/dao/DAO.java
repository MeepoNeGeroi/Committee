package org.example.model.dao;

import org.example.model.dao.exception.DAOException;

public interface DAO<T> {
    T read() throws DAOException;
}