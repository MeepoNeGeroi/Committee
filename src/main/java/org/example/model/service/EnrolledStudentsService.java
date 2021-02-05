package org.example.model.service;

import org.example.model.dao.impl.BillDAO;
import org.example.model.entity.Bill;
import org.example.model.entity.Enrollee;
import org.example.model.enums.Faculties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrolledStudentsService {
    public List<Enrollee> getEnrolledStudents(Faculties faculty) throws IOException {
        List<Enrollee> enrolledStudents = new ArrayList<>();
        List<Bill> bills = BillDAO.getInstance().read();
        List<Bill> sortedByFaculties = sortedByFaculties(bills, faculty);
        List<Bill> sortedByScore = sortedByScore(sortedByFaculties);

        for(int i = 0, k = 0; i < sortedByScore.size() && k < Faculties.getCount(faculty.toString()); i++, k++){
            enrolledStudents.add(sortedByScore.get(i).getEnrollee());
        }

        return enrolledStudents;
    }

    private List<Bill> sortedByScore(List<Bill> bills){
        Bill buf;

        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < bills.size() - 1; i++) {
                if(bills.get(i).getEnrollee().getScore() < bills.get(i + 1).getEnrollee().getScore()){
                    isSorted = false;
                    buf = bills.get(i);
                    bills.set(i,  bills.get(i + 1));
                    bills.set(i + 1,  buf);
                }
            }
        }

        return bills;
    }

    private List<Bill> sortedByFaculties(List<Bill> bills, Faculties faculty){
        List<Bill> sortedByFaculties = new ArrayList<>();

        for(int i = 0; i < bills.size(); i++){
            if(bills.get(i).getFaculty().getName().equals(faculty)){
                sortedByFaculties.add(bills.get(i));
            }
        }

        return sortedByFaculties;
    }
}