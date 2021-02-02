package org.example.Model.Entity;

import org.example.Model.DAO.DAO;
import org.example.Model.DAO.impl.BillDAO;
import org.example.Model.Faculties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Administrator {

    private List<Enrollee> studentsTeres = new ArrayList<>();
    private List<Enrollee> studentsPoit = new ArrayList<>();
    private List<Enrollee> studentsMintis = new ArrayList<>();
    private List<Bill> bills;

    public List<Enrollee> getStudentsTeres() {
        return studentsTeres;
    }

    public List<Enrollee> getStudentsPoit() {
        return studentsPoit;
    }

    public List<Enrollee> getStudentsMintis() {
        return studentsMintis;
    }

    public void get() throws IOException {
        DAO bill = new BillDAO();
        bill.read();
        bills = bill.getAll();

        studentsTeres = pushList(Faculties.тэрэс);
        studentsPoit = pushList(Faculties.поит);
        studentsMintis = pushList(Faculties.минтис);

        if(studentsTeres.size() > 0) {
            studentsTeres = sort(studentsTeres, Faculties.getCount("тэрэс"));
        }
        if(studentsPoit.size() > 0) {
            studentsPoit = sort(studentsPoit, Faculties.getCount("поит"));
        }
        if(studentsMintis.size() > 0) {
            studentsMintis = sort(studentsMintis, Faculties.getCount("минтис"));
        }
    }

    private List<Enrollee> pushList(Faculties faculty){
        List<Enrollee> enrollees = new ArrayList<>();

        for(int i = 0; i < bills.size(); i++){
            if(bills.get(i).getFaculty().getName().equals(faculty)){
                enrollees.add(bills.get(i).getEnrollee());
            }
        }

        return enrollees;
    }

    private List<Enrollee> sort(List<Enrollee> enrollees, int studentCount){
        boolean isSorted = false;
        Enrollee buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < enrollees.size() - 1; i++) {
                if(enrollees.get(i).score < enrollees.get(i + 1).score){
                    isSorted = false;

                    buf = enrollees.get(i);
                    enrollees.set(i, enrollees.get(i + 1));
                    enrollees.set(i + 1, buf);
                }
            }
        }

        enrollees = enrollees.subList(0, studentCount);

        return enrollees;
    }
}