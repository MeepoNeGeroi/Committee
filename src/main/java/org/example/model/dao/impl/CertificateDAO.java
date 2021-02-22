package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.JDBC;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Certificate;
import org.example.model.entity.Enrollee;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CertificateDAO implements EnrolleeInfoDAO<Certificate> {
    private static CertificateDAO instance;

    private CertificateDAO(){}

    public static CertificateDAO getInstance(){
        if(instance == null){
            instance = new CertificateDAO();
        }
        return instance;
    }

    @Override
    public List<Certificate> read() throws DAOException, SQLException {
        List<Certificate> certificates = new ArrayList<>();
        String certificateSql = "SELECT * FROM CERTIFICATE";

        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(certificateSql);

            while (result.next()) {
                double averageScore = result.getDouble(2);
                int firstSubject = result.getInt(3);
                int secondSubject = result.getInt(4);
                int thirdSubject = result.getInt(5);

                Certificate certificate = new Certificate.Builder().setAverageScore(averageScore)
                        .setFirstSubject(firstSubject)
                        .setSecondSubject(secondSubject)
                        .setThirdSubject(thirdSubject)
                        .build();

                certificates.add(certificate);
            }

            return certificates;
    }

    @Override
    public void delete(int index) throws DAOException, SQLException {
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        String sql = "DELETE FROM certificate WHERE certificateId = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, index + "");
        statement.executeUpdate();
    }

    @Override
    public void update(int index, Certificate certificate) throws DAOException, SQLException {
        String sql = "UPDATE certificate SET averageScore = ?" +
                ", firstSubject = ?, secondSubject = ?, thirdSubject = ? WHERE certificateId = ?";

        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, certificate.getAverageScore() + "");
        statement.setString(2, certificate.getFirstSubject()+ "");
        statement.setString(3, certificate.getSecondSubject() + "");
        statement.setString(4, certificate.getThirdSubject()+ "");
        statement.setString(5, index + "");
        statement.executeUpdate();
    }

    @Override
    public void create(Certificate certificate) throws DAOException, SQLException {
        int id = 0;
        Connection conn = DriverManager.getConnection(Const.dbURL, Const.username, Const.password);
        String sql = "INSERT INTO certificate VALUES(?, ?, ?, ?, ?)";
        String query = "SELECT certificateId FROM certificate";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(query);
        while(result.next()){
            id = result.getInt(1);
        }
        id+=1;
        String query2 = "UPDATE enrollee SET certificateId = ? WHERE enrolleeId = ?";
        PreparedStatement st = conn.prepareStatement(sql);
        st.setString(1, id + "");
        st.setString(2, certificate.getAverageScore() + "");
        st.setString(3, certificate.getFirstSubject() + "");
        st.setString(4, certificate.getSecondSubject() + "");
        st.setString(5, certificate.getThirdSubject() + "");
        st.executeUpdate();

        PreparedStatement st2 = conn.prepareStatement(query2);
        st2.setString(1, id + "");
        st2.setString(2, EnrolleeDAO.id + "");
        st2.executeUpdate();
//        List<String> text = readTextFromFile();
//        text.add(certificate.getAverageScore() + "");
//        text.add(certificate.getFirstSubject() + "");
//        text.add(certificate.getSecondSubject() + "");
//        text.add(certificate.getThirdSubject() + "");
//        writeTextInFile(text);
    }

    private List<String> readTextFromFile() throws DAOException {
        try {
            FileReader fr = new FileReader(Const.SUB_PATH);
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
            FileWriter fw = new FileWriter(Const.SUB_PATH);

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