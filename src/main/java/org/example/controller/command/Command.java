package org.example.controller.command;

import org.example.model.entity.Enrollee;
import org.example.model.enums.Faculties;
import org.example.model.service.EnrolledStudentsService;
import org.example.view.UI;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Command {
    public void dialog() throws IOException {
        Scanner sc = new Scanner(System.in);
        EnrolledStudentsService ess = new EnrolledStudentsService();
        UI ui = new UI();
        List<Enrollee> students;
        ui.mainDialog();
        int variant = sc.nextInt();
        while(variant != 2) {
            if(variant == 1) {
                try {
                    ui.chooseFacultyDialog();
                    String faculty = sc.next();
                    students = ess.getEnrolledStudents(Faculties.valueOf(faculty));
                    ui.enrolledStudentsInfo(students);
                }
                catch (Exception e){
                    System.out.println("Такого факультета нет в нашем учебном заведении!");
                }
            }
            else{
                System.out.println("Выбрананный пункт не существует!");
            }
            ui.mainDialog();
            variant = sc.nextInt();
        }
    }
}
