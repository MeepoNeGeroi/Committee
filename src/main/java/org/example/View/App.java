package org.example.View;

import org.example.Model.DAO.DAO;
import org.example.Model.DAO.impl.BillDAO;
import org.example.Model.DAO.impl.EnrolleeDAO;
import org.example.Model.Entity.Administrator;
import org.example.Model.Entity.Enrollee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
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

        Command command = new Command();
        command.info();
    }
}