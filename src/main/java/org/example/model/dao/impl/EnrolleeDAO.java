package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.DAO;
import org.example.model.entity.Enrollee;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolleeDAO implements DAO<Enrollee> {

    @Override
    public List<Enrollee> read() throws IOException {
        List<Enrollee> enrollees = new ArrayList<>();
        String name;
        int age;
        double score;

        FileReader fr = new FileReader(Const.AB_PATH);
        Scanner sc = new Scanner(fr);
        while(sc.hasNextLine()){
            name = sc.nextLine();
            age = Integer.parseInt(sc.nextLine());
            score = Double.parseDouble(sc.nextLine());

            enrollees.add(new Enrollee.Builder().setName(name).setAge(age).setScore(score).build());
        }
        fr.close();

        return enrollees;
    }

}
