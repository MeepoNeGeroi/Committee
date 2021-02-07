package org.example.view;

import org.example.controller.MainController;
import org.example.model.entity.User;

public class RegistrationAuthenticationUI {
    public void mainDialog(){
        System.out.println("1. Зарегистрироваться\n2. Войти\n0. Выйти");
    }

    public User registrationAuthenticationDialog(){
        System.out.println("Введите логин:");
        String login = "";
        while(login == ""){
            login = MainController.sc.nextLine();
        }
        System.out.println("Введите пароль:");
        String password = "";
        while(password.length() <= 0){
            password = MainController.sc.nextLine();
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        return user;
    }

    public void wrongLoginMessage(){
        System.out.println("Неверный логин!");
    }

    public void wrongMessage(){
        System.out.println("Неверный логин или пароль!");
    }
}
