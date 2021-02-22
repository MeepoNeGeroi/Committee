package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Faculty;
import org.example.model.enumeration.Faculties;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyDAO implements EnrolleeInfoDAO<Faculty> {
    private static FacultyDAO instance;

    private FacultyDAO(){}

    public static FacultyDAO getInstance(){
        if(instance == null){
            instance = new FacultyDAO();
        }
        return instance;
    }

    @Override
    public List<Faculty> read() throws SQLException {
        List<Faculty> faculties = new ArrayList<>();
        String facultySql = "SELECT * FROM FACULTY";

        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(facultySql);

            while (result.next()) {
                String name = result.getString(2);
                int studentCount = result.getInt(3);

                Faculty faculty = new Faculty.Builder()
                        .setName(Faculties.valueOf(name))
                        .setStudentCount(studentCount).build();
                faculties.add(faculty);
            }

            return faculties;
    }

    @Override
    public void delete(int index) throws DAOException {
        List<String> text = readTextFromFile();
        text.remove(index);
        writeTextInFile(text);
    }

    @Override
    public void update(int index, Faculty faculty) throws DAOException, SQLException {
        String sql = "SELECT facultyId FROM faculty WHERE name = ?";
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, faculty.getName() + "");
        ResultSet result = st.executeQuery();
        result.next();
        int facultyId = result.getInt(1);

        sql = "UPDATE enrollee SET facultyId = "
                + facultyId
                + " WHERE enrolleeId = "
                + index;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.executeUpdate();
    }

    @Override
    public void create(Faculty faculty) throws DAOException, SQLException {
        String sql = "SELECT facultyId FROM faculty WHERE name = ?";
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, faculty.getName() + "");
        ResultSet result = st.executeQuery();
        result.next();
        int facultyId = result.getInt(1);
        sql = "UPDATE enrollee SET facultyId = "
                + facultyId
                + " WHERE enrolleeId = "
                + EnrolleeDAO.id;

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.executeUpdate();
//        List<String> text = readTextFromFile();
//        text.add(faculty.getName().toString());
//        writeTextInFile(text);
    }

    private List<String> readTextFromFile() throws DAOException {
        try {
            FileReader fr = new FileReader(Const.FAC_PATH);
            Scanner sc = new Scanner(fr);
            List<String> text = new ArrayList<>();

            while (sc.hasNext()) {
                text.add(sc.nextLine());
            }

            fr.close();

            return text;
        }
        catch (Exception e){
            throw new DAOException();
        }
    }

    private void writeTextInFile(List<String> text) throws DAOException {
        try {
            FileWriter fw = new FileWriter(Const.FAC_PATH);

            for (int i = 0; i < text.size() - 1; i++) {
                fw.write(text.get(i) + "\n");
            }
            fw.write(text.get(text.size() - 1));
            fw.close();
        }
        catch (Exception e){
            throw new DAOException();
        }
    }
}
