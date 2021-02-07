package org.example.model.service;

import org.example.model.dao.impl.EnrolleeDAO;
import org.example.model.service.exception.ServiceException;

public class DeleteStudentService {
    private static DeleteStudentService instance;

    private DeleteStudentService(){}

    public static DeleteStudentService getInstance(){
        if(instance == null){
            instance = new DeleteStudentService();
        }

        return instance;
    }

    public void deleteStudent(int index) throws ServiceException {
        try {
            EnrolleeDAO.getInstance().delete(index);
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }
}
