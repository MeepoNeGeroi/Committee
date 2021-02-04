package org.example.view.command;

import org.example.model.entity.Enrollee;

import java.util.List;

public class EnrolledStudentsCommand {
    public void dialog(List<Enrollee> enrolledStudents){
        if(enrolledStudents.size() > 0) {
            System.out.println("Вот поступившие студенты: ");

            for (int i = 0; i < enrolledStudents.size(); i++) {
                System.out.println(enrolledStudents.get(i));
            }
        }
        else{
            System.out.println("Нет поступивших!");
        }
    }
}
