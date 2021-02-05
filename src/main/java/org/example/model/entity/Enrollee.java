package org.example.model.entity;

import java.util.Objects;

public class Enrollee {
    private String name;
    private int age;
    private Faculty faculty;
    private Certificate certificate;

    public Faculty getFaculty(){
        return faculty;
    }

    public void setFaculty(Faculty faculty){
        this.faculty = faculty;
    }

    public Certificate getCertificate(){
        return certificate;
    }

    public void setCertificate(Certificate certificate){
        this.certificate = certificate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Enrollee(){}

    @Override
    public int hashCode() {
        return Objects.hash(name, age, faculty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        return sb.append("Имя: ")
                .append(name)
                .append(" возраст: ")
                .append(age)
                .append(" ")
                .append(faculty)
                .append(" ")
                .append(certificate)
                .toString();
    }
}
