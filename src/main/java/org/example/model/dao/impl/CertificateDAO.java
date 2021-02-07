package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Certificate;

import java.io.FileReader;
import java.io.FileWriter;
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
    public List<Certificate> read() throws DAOException {
        try {
            List<Certificate> certificates = new ArrayList<>();
            double averageScore;
            int firstSubject;
            int secondSubject;
            int thirdSubject;

            FileReader fr = new FileReader(Const.SUB_PATH);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                averageScore = Double.parseDouble(sc.nextLine());
                firstSubject = Integer.parseInt(sc.nextLine());
                secondSubject = Integer.parseInt(sc.nextLine());
                thirdSubject = Integer.parseInt(sc.nextLine());

                certificates.add(new Certificate.Builder().setAverageScore(averageScore)
                        .setFirstSubject(firstSubject)
                        .setSecondSubject(secondSubject)
                        .setThirdSubject(thirdSubject)
                        .build());
            }
            fr.close();

            return certificates;
        }
        catch (Exception e){
            throw new DAOException();
        }
    }

    @Override
    public void delete(int index) throws DAOException {
        index *= 4;
        List<String> text = readTextFromFile();
        text.remove(index);
        text.remove(index);
        text.remove(index);
        text.remove(index);
        writeTextInFile(text);
    }

    @Override
    public void update(int index, Certificate certificate) throws DAOException {
        index *= 4;
        List<String> text = readTextFromFile();
        text.set(index, certificate.getAverageScore() + "");
        text.set(index + 1, certificate.getFirstSubject() + "");
        text.set(index + 2, certificate.getSecondSubject() + "");
        text.set(index + 3, certificate.getThirdSubject()+ "");
        writeTextInFile(text);
    }

    @Override
    public void create(Certificate certificate) throws DAOException {
        List<String> text = readTextFromFile();
        text.add(certificate.getAverageScore() + "");
        text.add(certificate.getFirstSubject() + "");
        text.add(certificate.getSecondSubject() + "");
        text.add(certificate.getThirdSubject() + "");
        writeTextInFile(text);
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