package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.Bill;
import org.example.model.entity.Committee;

public class CommitteeDAO implements DAO<Committee> {
    private static CommitteeDAO instance;

    private CommitteeDAO() throws DAOException {}

    public static CommitteeDAO getInstance() throws DAOException{
        if(instance == null){
            instance = new CommitteeDAO();
        }
        return instance;
    }

    @Override
    public Committee read() throws DAOException {
        Bill bill = BillDAO.getInstance().read();
        Committee committee = Committee.getInstance();
        committee.setBill(bill);
        return committee;
    }
}