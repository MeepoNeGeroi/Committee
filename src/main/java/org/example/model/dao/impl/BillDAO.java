package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.Administrator;
import org.example.model.entity.Bill;

import java.io.IOException;

public class BillDAO implements DAO<Bill> {
    private Administrator administrator = AdministratorDAO.getInstance().read();
    private static BillDAO instance;

    private BillDAO() throws IOException {}

    public static BillDAO getInstance() throws IOException {
        if(instance == null){
            instance = new BillDAO();
        }
        return instance;
    }

    @Override
    public Bill read() throws IOException {
        Bill bill = Bill.getInstance();
        bill.setAdministrator(administrator);

        return bill;
    }
}