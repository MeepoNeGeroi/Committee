package org.example.View;

import org.example.Model.Entity.Administrator;
import org.example.Model.Entity.Enrollee;

import java.io.IOException;
import java.util.List;

public class Command {
    public void info() throws IOException {
        Administrator administrator = new Administrator();
        administrator.get();

        List<Enrollee> studentTeres = administrator.getStudentsTeres();
        List<Enrollee> studentPoit = administrator.getStudentsPoit();
        List<Enrollee> studentMintis = administrator.getStudentsMintis();

        System.out.println("ТЭРЭС:");
        showList(studentTeres);
        System.out.println("ПОИТ:");
        showList(studentPoit);
        System.out.println("МИНТИС:");
        showList(studentMintis);
    }

    private void showList(List<Enrollee> enrollees){
        for (int i = 0; i < enrollees.size() ; i++) {
            System.out.println(enrollees.get(i));
        }
    }
}
