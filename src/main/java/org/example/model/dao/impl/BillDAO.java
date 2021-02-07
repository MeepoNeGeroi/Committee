package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Administrator;
import org.example.model.entity.Bill;

public class BillDAO implements DAO<Bill> {
    private static BillDAO instance;

    private BillDAO() {}

    public static BillDAO getInstance() {
        if(instance == null){
            instance = new BillDAO();
        }
        return instance;
    }

    @Override
    public Bill read() throws DAOException {
        Administrator administrator = AdministratorDAO.getInstance().read();
        Bill bill = new Bill();
        bill.setAdministrator(administrator);

        return bill;
    }
}