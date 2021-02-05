package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.Bill;
import org.example.model.entity.Committee;

import java.io.IOException;

public class CommitteeDAO implements DAO<Committee> {
    private Bill bill = BillDAO.getInstance().read();
    private static CommitteeDAO instance;

    private CommitteeDAO() throws IOException {}

    public static CommitteeDAO getInstance() throws IOException {
        if(instance == null){
            instance = new CommitteeDAO();
        }
        return instance;
    }

    @Override
    public Committee read() throws IOException {
        Committee committee = Committee.getInstance();
        committee.setBill(bill);
        return committee;
    }
}