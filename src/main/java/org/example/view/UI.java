package org.example.view;

import org.example.controller.MainController;
import org.example.model.entity.Certificate;
import org.example.model.entity.Enrollee;
import org.example.model.entity.Faculty;
import org.example.model.enumeration.Faculties;

import java.util.List;

public class UI {
    public void mainDialog(){
        System.out.println("1. Вывести поступивших\n2. Удалить абитуриента\n3. Добавить абитуриента\n"
                +"4. Обновить абитуриента\n"
                +"0. Выйти");
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

    public void deleteStudentDialog(){
        System.out.println("Введите индекс учащегося:");
    }

    public Enrollee addStudentDialog(){
        System.out.println("Введите фамилию имя абитуриента: ");
        String name = MainController.sc.nextLine();
        if(name.length() <=0){
            name = MainController.sc.nextLine();
        }
        System.out.println("Введите возраст абитуриента: ");
        int age = MainController.sc.nextInt();
        System.out.println("Введите факультет абитуриента: ");
        Faculties facultyName = Faculties.valueOf(MainController.sc.next());
        System.out.println("Введите средний балл и три отметки по предметам: ");
        int averageScore = MainController.sc.nextInt();
        int firstSubjectScore = MainController.sc.nextInt();
        int secondSubjectScore = MainController.sc.nextInt();
        int thirdSubjectScore = MainController.sc.nextInt();

        Faculty faculty = new Faculty.Builder().setName(facultyName).build();
        Certificate certificate = new Certificate.Builder().setAverageScore(averageScore)
                .setFirstSubject(firstSubjectScore)
                .setSecondSubject(secondSubjectScore)
                .setThirdSubject(thirdSubjectScore)
                .build();
        Enrollee enrollee = new Enrollee();
        enrollee.setCertificate(certificate);
        enrollee.setFaculty(faculty);
        enrollee.setName(name);
        enrollee.setAge(age);

        return enrollee;
    }

    public void updateStudentDialog(){
        System.out.println("Введите индекс абитуриента: ");
    }

}
