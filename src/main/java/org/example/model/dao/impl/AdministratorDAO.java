package org.example.model.dao.impl;

import org.example.model.dao.DAO;
import org.example.model.entity.*;

import java.io.IOException;
import java.util.List;

public class AdministratorDAO implements DAO<Administrator> {
    private static AdministratorDAO instance;
    private List<Enrollee> enrollees = EnrolleeDAO.getInstance().read();

    private AdministratorDAO() throws IOException {}

    public static AdministratorDAO getInstance() throws IOException {
        if(instance == null){
            instance = new AdministratorDAO();
        }
        return instance;
    }

    @Override
    public Administrator read() {
        Administrator administrator = new Administrator.Builder().build();
        List<Enrollee> enrollees = this.enrollees;

        for(int i = 0; i < enrollees.size() && i < enrollees.size(); i++){
            administrator = new Administrator.Builder()
                    .setEnrollee(enrollees.get(i))
                    .build();
        }

        return administrator;
    }
}