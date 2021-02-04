package org.example.model.dao;

import java.io.IOException;
import java.util.List;

public interface DAO<T> {
    List<T> read() throws IOException;
}
