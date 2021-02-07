package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.*;
import org.example.model.dao.exception.DAOException;

import java.util.List;

public class AdministratorDAO implements DAO<Administrator> {
    private static AdministratorDAO instance;

    private AdministratorDAO(){}

    public static AdministratorDAO getInstance() {
        if(instance == null){
            instance = new AdministratorDAO();
        }
        return instance;
    }

    @Override
    public Administrator read() throws DAOException {
        List<Enrollee> enrollees = EnrolleeDAO.getInstance().read();
        Administrator administrator = new Administrator.Builder().build();

        for(int i = 0; i < enrollees.size(); i++){
            List<Enrollee> buf = administrator.getEnrollee();
            buf.add(enrollees.get(i));
            administrator.setEnrollee(buf);
        }

        return administrator;
    }
}