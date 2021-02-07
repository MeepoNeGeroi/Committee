package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.entity.Certificate;
import org.example.model.entity.Enrollee;
import org.example.model.entity.Faculty;
import org.example.model.dao.exception.DAOException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolleeDAO implements EnrolleeInfoDAO<Enrollee> {
    private static EnrolleeDAO instance;

    private EnrolleeDAO() throws DAOException {}

    public static EnrolleeDAO getInstance() throws DAOException {
        if(instance == null){
            instance = new EnrolleeDAO();
        }
        return instance;
    }

    @Override
    public List<Enrollee> read() throws DAOException{
        try {
            List<Faculty> faculties = FacultyDAO.getInstance().read();
            List<Certificate> certificates = CertificateDAO.getInstance().read();
            List<Enrollee> enrollees = new ArrayList<>();
            String name;
            int i = 0;
            int age;

            FileReader fr = new FileReader(Const.AB_PATH);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                name = sc.nextLine();
                age = Integer.parseInt(sc.nextLine());

                Enrollee enrollee = new Enrollee();
                enrollee.setAge(age);
                enrollee.setName(name);
                enrollee.setFaculty(faculties.get(i));
                enrollee.setCertificate(certificates.get(i));
                i++;
                if (enrollee != null) {
                    enrollees.add(enrollee);
                }
            }
            fr.close();

            return enrollees;
        }
        catch (Exception e){
            throw new DAOException();
        }
    }

    @Override
    public void delete(int index) throws DAOException {
        List<String> text = readTextFromFile();
        text.remove(index * 2);
        text.remove(index * 2);
        writeTextInFile(text);

        FacultyDAO.getInstance().delete(index);
        CertificateDAO.getInstance().delete(index);
    }

    @Override
    public void update(int index, Enrollee enrollee) throws DAOException {
        List<String> text = readTextFromFile();
        text.set(index * 2, enrollee.getName());
        text.set(index * 2 + 1, enrollee.getAge() + "");
        writeTextInFile(text);

        FacultyDAO.getInstance().update(index, enrollee.getFaculty());
        CertificateDAO.getInstance().update(index, enrollee.getCertificate());
    }

    @Override
    public void create(Enrollee enrollee) throws DAOException {
        List<String> text = readTextFromFile();
        text.add(enrollee.getName());
        text.add(enrollee.getAge() + "");
        writeTextInFile(text);

        FacultyDAO.getInstance().create(enrollee.getFaculty());
        CertificateDAO.getInstance().create(enrollee.getCertificate());
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