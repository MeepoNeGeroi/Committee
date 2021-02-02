package org.example.Model.DAO.impl;

import org.example.Model.DAO.DAO;
import org.example.Model.Entity.Bill;
import org.example.Model.Entity.Enrollee;
import org.example.Model.Entity.Faculty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill> {

    public static List<Bill> bills = new ArrayList<>();

    @Override
    public List<Bill> getAll() {
        return bills;
    }

    @Override
    public void read() throws IOException {
        DAO enr = new EnrolleeDAO();
        DAO fac = new FacultyDAO();
        enr.read();
        fac.read();
        List<Enrollee> enrollees = enr.getAll();
        List<Faculty> faculties = fac.getAll();

        for(int i = 0; i < enrollees.size() && i < faculties.size(); i++){
            bills.add(new Bill.Builder().setEnrollee(enrollees.get(i)).setFaculty(faculties.get(i)).build());
        }
    }

    @Override
    public void update(Bill bill, String[] params) {

    }

    @Override
    public void delete(Bill bill) {

    }
}
