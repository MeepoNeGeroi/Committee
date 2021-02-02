package org.example.Model.DAO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    void read() throws IOException;
    void update(T t, String[] params);
    void delete(T t);
}
