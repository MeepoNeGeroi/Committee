package org.example.view.command;

import java.util.Scanner;

public class DialogCommand {
    public int dialog(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Вывести поступивших\n2. Выйти");
        int variant = sc.nextInt();

        return variant;
    }
}
