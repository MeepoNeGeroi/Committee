package org.example.model.service;

import org.example.model.Const;
import org.example.model.dao.impl.UserDAO;
import org.example.model.entity.User;
import org.example.model.service.exception.ServiceException;
import org.example.view.RegistrationAuthenticationUI;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationService {
    private static RegistrationService instance;

    private RegistrationService(){}

    public static RegistrationService getInstance(){
        if(instance == null){
            instance = new RegistrationService();
        }

        return instance;
    }

    public boolean registration(User user) throws ServiceException {
        try {
            List<User> users = UserDAO.getInstance().read();

            if (!checkLogin(user.getLogin(), users)) {
                RegistrationAuthenticationUI registrationAuthenticationUI = new RegistrationAuthenticationUI();
                registrationAuthenticationUI.wrongLoginMessage();
                return false;
            }
            UserDAO.getInstance().create(user);
            return true;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    private boolean checkLogin(String login, List<User> users){
        for(int i = 0; i < users.size(); i += 2){
            if(login.equals(users.get(i).getLogin())){
                return false;
            }
        }

        return true;
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

    private void writeTextInFile(List<String> text) throws ServiceException {
        try {
            FileWriter fw = new FileWriter(Const.USER_PATH);

            for (int i = 0; i < text.size() - 1; i++) {
                fw.write(text.get(i) + "\n");
            }
            fw.write(text.get(text.size() - 1));
            fw.close();
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }
}
