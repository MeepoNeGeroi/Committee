package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.DAO;
import org.example.model.entity.Faculty;
import org.example.model.enums.Faculties;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyDAO implements DAO<Faculty> {
    private static FacultyDAO instance;
    private FacultyDAO(){}
    public static FacultyDAO getInstance(){
        if(instance == null){
            instance = new FacultyDAO();
        }
        return instance;
    }

    @Override
    public List<Faculty> read() throws IOException {
        List<Faculty> faculties = new ArrayList<>();
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
                System.out.println("Неверный факультет.");
                faculties.add(new Faculty.Builder().setName(Faculties.teres)
                        .setStudentCount(studentCount).build());
            }
        }
        fr.close();

        return faculties;
    }
}
