package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.entity.Certificate;
import org.example.model.entity.Enrollee;
import org.example.model.entity.Faculty;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolleeDAO implements EnrolleeInfoDAO<Enrollee> {
    private List<Faculty> faculties = FacultyDAO.getInstance().read();
    private List<Certificate> certificates = CertificateDAO.getInstance().read();
    private static EnrolleeDAO instance;

    private EnrolleeDAO() throws IOException {}

    public static EnrolleeDAO getInstance() throws IOException {
        if(instance == null){
            instance = new EnrolleeDAO();
        }
        return instance;
    }

    @Override
    public List<Enrollee> read() throws IOException {
        List<Enrollee> enrollees = new ArrayList<>();
        String name;
        int i = 0;
        int age;

        FileReader fr = new FileReader(Const.AB_PATH);
        Scanner sc = new Scanner(fr);
        while(sc.hasNextLine()){
            name = sc.nextLine();
            age = Integer.parseInt(sc.nextLine());

            Enrollee enrollee = new Enrollee();
            enrollee.setAge(age);
            enrollee.setName(name);
            enrollee.setFaculty(faculties.get(i));
            enrollee.setCertificate(certificates.get(i));
            i++;
            if(enrollee != null) {
                enrollees.add(enrollee);
            }
        }
        fr.close();

        return enrollees;
    }

}