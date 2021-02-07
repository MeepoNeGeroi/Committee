package org.example.model.service;

import org.example.model.dao.exception.DAOException;
import org.example.model.dao.impl.EnrolleeDAO;
import org.example.model.entity.Enrollee;
import org.example.model.service.exception.ServiceException;

public class UpdateStudentService {
    private static UpdateStudentService instance;

    private UpdateStudentService(){}

    public static UpdateStudentService getInstance(){
        if(instance == null){
            instance = new UpdateStudentService();
        }

        return instance;
    }

    public void updateStudent(int index, Enrollee enrollee) throws ServiceException {
        try {
            EnrolleeDAO.getInstance().update(index, enrollee);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }
}
