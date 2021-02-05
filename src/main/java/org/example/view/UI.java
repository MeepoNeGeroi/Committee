package org.example.view;

import org.example.model.entity.Enrollee;

import java.util.List;

public class UI {
    public void mainDialog(){
        System.out.println("1. Вывести поступивших\n2. Выйти");
    }

    public void enrolledStudentsInfo(List<Enrollee> enrolledStudents){
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

    public void chooseFacultyDialog(){
        System.out.println("Введите название факультета:");
    }
}
