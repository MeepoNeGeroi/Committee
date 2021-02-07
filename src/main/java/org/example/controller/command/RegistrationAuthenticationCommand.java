package org.example.controller.command;

import org.example.controller.MainController;
import org.example.controller.exception.ControllerException;
import org.example.model.entity.User;
import org.example.model.service.RegistrationService;
import org.example.model.service.SignInService;
import org.example.view.RegistrationAuthenticationUI;

public class RegistrationAuthenticationCommand {
    public boolean dialog() throws ControllerException {
        try {
            User user;
            RegistrationAuthenticationUI ui = new RegistrationAuthenticationUI();
            boolean isSignIn = false;

            ui.mainDialog();
            int variant = MainController.sc.nextInt();
            while (!isSignIn) {
                if (variant == 1) {
                    user = ui.registrationAuthenticationDialog();
                    isSignIn = RegistrationService.getInstance().registration(user);
                } else if (variant == 2) {
                    user = ui.registrationAuthenticationDialog();
                    isSignIn = SignInService.getInstance().authentication(user);
                } else if (variant == 0) {
                    return false;
                }

                if (isSignIn) {
                    return true;
                }

                ui.mainDialog();
                variant = MainController.sc.nextInt();
            }

            return true;
        }
        catch (Exception e){
            throw new ControllerException();
        }
    }
}
