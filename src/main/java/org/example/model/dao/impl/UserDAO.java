package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.DAO;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements EnrolleeInfoDAO<User> {
    private static UserDAO instance;

    private UserDAO(){}

    public static UserDAO getInstance() {
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }


    @Override
    public List<User> read() throws DAOException, SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while(result.next()){
            String login = result.getString(2);
            String password = result.getString(3);
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            users.add(user);
        }

        return users;
    }

    @Override
    public void delete(int index) throws DAOException, SQLException {
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        String sql = "DELETE FROM user WHERE userId = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, index + "");
        statement.executeUpdate();
    }

    @Override
    public void update(int index, User user) throws DAOException, SQLException {
        //int id = 0;
        //String sql = "SELECT userId FROM user";
        String query = "UPDATE user SET login = ?, password = ? WHERE userId = ?";
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        //PreparedStatement statement = conn.prepareStatement(query);
        PreparedStatement st = conn.prepareStatement(query);
//        ResultSet result = statement.executeQuery();

//        while(result.next()){
//            id = result.getInt(1);
//        }

        st.setString(1, user.getLogin());
        st.setString(2, user.getPassword());
        st.setString(3, index + "");

        st.executeUpdate();
    }

    @Override
    public void create(User user) throws DAOException, SQLException {
        int id = 0;
        String sql = "SELECT userId FROM user";
        String query = "INSERT INTO user VALUES (?, ?, ?)";
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        PreparedStatement statement = conn.prepareStatement(sql);
        PreparedStatement st = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();

        while(result.next()){
            id = result.getInt(1);
        }
        id++;

        st.setString(1, id + "");
        st.setString(2, user.getLogin());
        st.setString(3, user.getPassword());

        st.executeUpdate();
    }
}
