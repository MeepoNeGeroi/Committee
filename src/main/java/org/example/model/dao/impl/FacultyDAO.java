package org.example.model.dao.impl;

import org.example.model.Const;
import org.example.model.dao.EnrolleeInfoDAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Faculty;
import org.example.model.enumeration.Faculties;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FacultyDAO implements EnrolleeInfoDAO<Faculty> {
    private static FacultyDAO instance;

    private FacultyDAO(){}

    public static FacultyDAO getInstance(){
        if(instance == null){
            instance = new FacultyDAO();
        }
        return instance;
    }

    @Override
    public List<Faculty> read() throws DAOException {
        List<Faculty> faculties = new ArrayList<>();
        try {
            String name;
            int studentCount;

            FileReader fr = new FileReader(Const.FAC_PATH);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                name = sc.nextLine();
                studentCount = Faculties.getCount(name);
                faculties.add(new Faculty.Builder().setName(Faculties.valueOf(name.toUpperCase()))
                        .setStudentCount(studentCount).build());
                fr.close();

                return faculties;
            }
        }
        catch(Exception e){
            throw new DAOException();
        }
        return faculties;
    }

    @Override
    public void delete(int index) throws DAOException {
        List<String> text = readTextFromFile();
        text.remove(index);
        writeTextInFile(text);
    }

    @Override
    public void update(int index, Faculty faculty) throws DAOException {
        List<String> text = readTextFromFile();
        text.set(index, faculty.getName().toString());
        writeTextInFile(text);
    }

    @Override
    public void create(Faculty faculty) throws DAOException {
        List<String> text = readTextFromFile();
        text.add(faculty.getName().toString());
        writeTextInFile(text);
    }

    private List<String> readTextFromFile() throws DAOException {
        try {
            FileReader fr = new FileReader(Const.FAC_PATH);
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
            FileWriter fw = new FileWriter(Const.FAC_PATH);

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
