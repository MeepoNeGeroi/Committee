package org.example.model.service;

import org.example.model.Const;
import org.example.model.dao.exception.DAOException;
import org.example.model.entity.User;
import org.example.model.service.exception.ServiceException;
import org.example.view.RegistrationAuthenticationUI;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            List<String> text = readTextFromFile();
            for (int i = 0; i < text.size() - 1; i++) {
                if (user.getLogin().equals(text.get(i)) && user.getPassword().equals(text.get(i + 1))) {
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

    private List<String> readTextFromFile() throws ServiceException {
        try {
            FileReader fr = new FileReader(Const.USER_PATH);
            Scanner sc = new Scanner(fr);
            List<String> text = new ArrayList<>();

            while (sc.hasNext()) {
                text.add(sc.nextLine());
            }

            fr.close();

            return text;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }
}
