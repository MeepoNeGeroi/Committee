package org.example.controller.command;

import org.example.controller.exception.ControllerException;
import org.example.model.dao.impl.EnrolleeDAO;
import org.example.model.entity.Enrollee;
import org.example.model.enumeration.Faculties;
import org.example.model.service.DeleteStudentService;
import org.example.model.service.EnrolledStudentsService;
import org.example.view.UI;

import java.util.List;
import java.util.Scanner;

public class EnrolleeCommand {
    public void dialog() throws ControllerException {
        try {
            Scanner sc = new Scanner(System.in);
            EnrolledStudentsService ess = EnrolledStudentsService.getInstance();
            UI ui = new UI();
            List<Enrollee> students;
            ui.mainDialog();
            int variant = sc.nextInt();
            while (variant != 0) {
                if (variant == 1) {
                    try {
                        ui.chooseFacultyDialog();
                        String faculty = sc.next();
                        students = ess.getEnrolledStudents(Faculties.valueOf(faculty));
                        ui.enrolledStudentsInfo(students);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Такого факультета нет в нашем учебном заведении!");
                    }
                } else if (variant == 2) {
                    ui.deleteStudentDialog();
                    int index = sc.nextInt();
                    DeleteStudentService.getInstance().deleteStudent(index);
                    System.out.println("Абитуриент был удален!");
                } else if (variant == 3) {
                    Enrollee enrollee = ui.addStudentDialog();
                    EnrolleeDAO.getInstance().create(enrollee);
                    System.out.println("Абитуриент был добавлен!");
                } else if (variant == 4) {
                    ui.updateStudentDialog();
                    int index = sc.nextInt();
                    Enrollee enrollee = ui.addStudentDialog();
                    EnrolleeDAO.getInstance().update(index, enrollee);
                    System.out.println("Абитуриент был изменен!");
                } else {
                    System.out.println("Выбрананный пункт не существует!");
                }
                ui.mainDialog();
                variant = sc.nextInt();
            }
        }
        catch (Exception e){
            throw new ControllerException();
        }
    }
}
