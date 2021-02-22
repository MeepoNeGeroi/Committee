package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.JDBC;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.entity.Certificate;
import org.example.model.entity.Enrollee;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Faculty;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolleeDAO implements EnrolleeInfoDAO<Enrollee> {
    public static  int id;
    private static EnrolleeDAO instance;

    private EnrolleeDAO() throws DAOException {}

    public static EnrolleeDAO getInstance() throws DAOException {
        if(instance == null){
            instance = new EnrolleeDAO();
        }
        return instance;
    }

    @Override
    public List<Enrollee> read() throws DAOException, SQLException {
        List<Enrollee> enrollees = new ArrayList<>();
        List<Faculty> faculties = FacultyDAO.getInstance().read();
        List<Certificate> certificates = CertificateDAO.getInstance().read();
        String enrolleeSql = "SELECT * FROM ENROLLEE";
        Connection conn = new JDBC().getConnection();

        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(enrolleeSql);

        while(result.next()){
            String name = result.getString(2);
            int age = result.getInt(3);
            Enrollee enrollee = new Enrollee();
            enrollee.setAge(age);
            enrollee.setName(name);
            enrollee.setFaculty(faculties.get(result.getInt(4) - 1));
            enrollee.setCertificate(certificates.get(result.getInt(5) - 1));
            enrollees.add(enrollee);
        }

        return enrollees;
    }

    @Override
    public void delete(int index) throws DAOException, SQLException {
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        String sql = "DELETE FROM enrollee WHERE enrolleeId = ?";
        String certificateQuery = "SELECT certificateId FROM enrollee WHERE enrolleeId = ?";
        PreparedStatement statement = conn.prepareStatement(certificateQuery);
        statement.setString(1, index + "");
        ResultSet result = statement.executeQuery();
        result.next();
        CertificateDAO.getInstance().delete(result.getInt(1));

        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, index + "");
        st.executeUpdate();
    }

    @Override
    public void update(int index, Enrollee enrollee) throws DAOException, SQLException {
        String sql = "UPDATE enrollee SET name = ?, age = ?, facultyId = ? WHERE enrolleeId = ?";
        String idQuery = "SELECT certificateId FROM enrollee WHERE enrolleeId = " + index;
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, enrollee.getName());
        statement.setString(2, enrollee.getAge() + "");
        statement.setString(3, "1");
        statement.setString(4, index + "");
        statement.executeUpdate();
        Statement st = conn.createStatement();
        FacultyDAO.getInstance().update(index, enrollee.getFaculty());
        ResultSet result = st.executeQuery(idQuery);
        result.next();
        index = result.getInt(1);
        CertificateDAO.getInstance().update(index, enrollee.getCertificate());
    }

    @Override
    public void create(Enrollee enrollee) throws DAOException, SQLException {
        int id = 0;
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        String sql = "SELECT enrolleeId FROM enrollee";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            id = result.getInt(1);
        }
        id++;
        this.id = id;
        String createQuery = "INSERT INTO enrollee VALUES(?, ?, ?, 0, 0)";
        PreparedStatement st = conn.prepareStatement(createQuery);
        st.setString(1, id + "");
        st.setString(2, enrollee.getName());
        st.setString(3, enrollee.getAge() + "");
        st.executeUpdate();
        FacultyDAO.getInstance().create(enrollee.getFaculty());
        CertificateDAO.getInstance().create(enrollee.getCertificate());
//        List<String> text = readTextFromFile();
//        text.add(enrollee.getName());
//        text.add(enrollee.getAge() + "");
//        writeTextInFile(text);
//
//        FacultyDAO.getInstance().create(enrollee.getFaculty());
//        CertificateDAO.getInstance().create(enrollee.getCertificate());
    }

    private List<String> readTextFromFile() throws DAOException {
        try {
            FileReader fr = new FileReader(Const.AB_PATH);
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
            FileWriter fw = new FileWriter(Const.AB_PATH);

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