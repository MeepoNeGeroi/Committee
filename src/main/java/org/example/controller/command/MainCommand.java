package org.example.controller.command;

import org.example.controller.exception.ControllerException;

public class MainCommand {
    public void main() throws ControllerException {
        try {
            RegistrationAuthenticationCommand registrationAuthenticationCommand = new RegistrationAuthenticationCommand();
            if (registrationAuthenticationCommand.dialog()) {
                EnrolleeCommand enrolleeCommand = new EnrolleeCommand();
                enrolleeCommand.dialog();
            }

            System.out.println("Спасибо за использование приложения!");
        }
        catch (Exception e){
            throw new ControllerException();
        }
    }
}