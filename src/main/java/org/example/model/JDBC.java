package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    public Connection getConnection() throws SQLException {
        String dbURL = "jdbc:mysql://localhost/db";
        String username = "root";
        String password = "28072003";

        Connection conn = DriverManager.getConnection(dbURL, username, password);
        return conn;
    }
}
