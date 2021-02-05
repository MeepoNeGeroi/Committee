package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.Bill;
import org.example.model.entity.Enrollee;
import org.example.model.entity.Faculty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BillDAO implements DAO<Bill> {
    private List<Enrollee> enr = EnrolleeDAO.getInstance().read();
    private List<Faculty> fac = FacultyDAO.getInstance().read();

    private static BillDAO instance;
    private BillDAO() throws IOException {}
    public static BillDAO getInstance() throws IOException {
        if(instance == null){
            instance = new BillDAO();
        }
        return instance;
    }

    @Override
    public List<Bill> read() throws IOException {
        List<Bill> bills = new ArrayList<>();
        List<Enrollee> enrollees = enr;
        List<Faculty> faculties = fac;

        for(int i = 0; i < enrollees.size() && i < faculties.size(); i++){
            bills.add(new Bill.Builder().setEnrollee(enrollees.get(i)).setFaculty(faculties.get(i)).build());
        }

        return bills;
    }
}