package org.example.model.service;

import org.example.model.dao.impl.UserDAO;
import org.example.model.entity.User;
import org.example.model.service.exception.ServiceException;
import org.example.view.RegistrationAuthenticationUI;
import java.util.List;

public class SignInService {
    private static SignInService instance;

    private SignInService(){}

    public static SignInService getInstance(){
        if(instance == null){
            instance = new SignInService();
        }

        return instance;
    }

    public boolean authentication(User user) throws ServiceException {
        try {
            List<User> users = UserDAO.getInstance().read();
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).equals(user)) {
                    return true;
                }
            }

            RegistrationAuthenticationUI ui = new RegistrationAuthenticationUI();
            ui.wrongMessage();

            return false;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }
}
