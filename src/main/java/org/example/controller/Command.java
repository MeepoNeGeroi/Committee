package org.example.controller;

import org.example.model.entity.Enrollee;
import org.example.model.enums.Faculties;
import org.example.model.service.EnrolledStudentsService;
import org.example.view.command.DialogCommand;
import org.example.view.command.EnrolledStudentsCommand;
import org.example.view.command.FacultyCommand;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Command {
    public void dialog() throws IOException {
        Scanner sc = new Scanner(System.in);
        DialogCommand dialogCommand = new DialogCommand();
        FacultyCommand facultyCommand = new FacultyCommand();
        EnrolledStudentsService ess = new EnrolledStudentsService();
        List<Enrollee> students;
        int variant = dialogCommand.dialog();

        while(variant != 2) {
            if(variant == 1) {
                try {
                    facultyCommand.dialog();
                    String faculty = sc.nextLine();
                    students = ess.getEnrolledStudents(Faculties.valueOf(faculty));
                    EnrolledStudentsCommand enrolledStudentsCommand = new EnrolledStudentsCommand();
                    enrolledStudentsCommand.dialog(students);
                }
                catch (Exception e){
                    System.out.println("Такого факультета нет в нашем учебном заведении!");
                }
            }
            else{
                System.out.println("Выбрананный пункт не существует!");
            }

            variant = dialogCommand.dialog();
        }
    }
}
