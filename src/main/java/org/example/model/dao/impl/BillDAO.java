package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.Bill;
import org.example.model.entity.Enrollee;
import org.example.model.entity.Faculty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill> {

    @Override
    public List<Bill> read() throws IOException {
        List<Bill> bills = new ArrayList<>();
        DAO enr = new EnrolleeDAO();
        DAO fac = new FacultyDAO();
        enr.read();
        fac.read();
        List<Enrollee> enrollees = enr.read();
        List<Faculty> faculties = fac.read();

        for(int i = 0; i < enrollees.size() && i < faculties.size(); i++){
            bills.add(new Bill.Builder().setEnrollee(enrollees.get(i)).setFaculty(faculties.get(i)).build());
        }

        return bills;
    }
}
