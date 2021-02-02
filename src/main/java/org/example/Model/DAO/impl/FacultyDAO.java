package org.example.Model.DAO.impl;

import org.example.Const;
import org.example.Model.DAO.DAO;
import org.example.Model.Entity.Faculty;
import org.example.Model.Faculties;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyDAO implements DAO<Faculty> {

    private List<Faculty> faculties = new ArrayList<>();

    @Override
    public List<Faculty> getAll() {
        return faculties;
    }

    @Override
    public void read() throws IOException {
        String name;
        int studentCount;

        FileReader fr = new FileReader(Const.FAC_PATH);
        Scanner sc = new Scanner(fr);
        while(sc.hasNextLine()){
            name = sc.nextLine();
            studentCount = Faculties.getCount(name);
            try {
                faculties.add(new Faculty.Builder().setName(Faculties.valueOf(name.toLowerCase()))
                        .setStudentCount(studentCount).build());
            }
            catch (Exception e){
                System.out.println("Неверный факультатив.");
                faculties.add(new Faculty.Builder().setName(Faculties.тэрэс)
                        .setStudentCount(studentCount).build());
            }
        }
        fr.close();
    }

    @Override
    public void update(Faculty faculty, String[] params) {

    }

    @Override
    public void delete(Faculty faculty) {
        faculties.remove(faculty);
    }
}
