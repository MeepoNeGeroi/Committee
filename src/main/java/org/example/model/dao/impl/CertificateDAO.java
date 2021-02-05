package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.entity.Certificate;

import java.io.FileReader;
import java.io.IOException;
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
    public List<Certificate> read() throws IOException {
        List<Certificate> certificates = new ArrayList<>();
        double averageScore;
        int firstSubject;
        int secondSubject;
        int thirdSubject;

        FileReader fr = new FileReader(Const.SUB_PATH);
        Scanner sc = new Scanner(fr);
        while(sc.hasNextLine()){
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
}
