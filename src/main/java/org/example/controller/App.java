package org.example.controller;

import org.example.controller.command.Command;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {
//        DAO bill = new BillDAO();
//        bill.read();
//        List<Enrollee> enrollees = bill.getAll();
//
//
//        for (int i = 0; i < enrollees.size(); i++) {
//            System.out.println(enrollees.get(i));
//        }

//        Administrator administrator = new Administrator();
//        administrator.get();

//        Command command = new Command();
//        command.info();

        Command command = new Command();
        command.dialog();
    }
}