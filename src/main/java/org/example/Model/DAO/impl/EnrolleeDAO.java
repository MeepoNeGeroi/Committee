package org.example.Model.DAO.impl;

import org.example.Const;
import org.example.Model.DAO.DAO;
import org.example.Model.Entity.Enrollee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolleeDAO implements DAO<Enrollee> {

    private List<Enrollee> enrollees = new ArrayList<>();

    @Override
    public List<Enrollee> getAll() {
        return enrollees;
    }

    @Override
    public void read() throws IOException {
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
    }

    @Override
    public void update(Enrollee enrollee, String[] params) {

    }

    @Override
    public void delete(Enrollee enrollee) {
        enrollees.remove(enrollee);
    }
}
