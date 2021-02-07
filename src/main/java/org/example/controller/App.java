package org.example.controller;

import org.example.controller.command.MainCommand;
import org.example.controller.exception.ControllerException;

public class App 
{
    public static void main( String[] args ) throws ControllerException {
        MainCommand mainCommand = new MainCommand();
        mainCommand.main();
    }
}