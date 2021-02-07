package org.example.model.dao;

import org.example.model.dao.exception.DAOException;

import java.io.FileNotFoundException;
import java.util.List;

public interface EnrolleeInfoDAO<T> {
    List<T> read() throws DAOException;
    void delete(int index) throws DAOException;
    void update(int index, T t) throws DAOException;
    void create(T t) throws DAOException;
}
