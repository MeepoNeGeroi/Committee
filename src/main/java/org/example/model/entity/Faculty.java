package org.example.model.entity;

import org.example.model.enums.Faculties;

import java.util.Objects;

public class Faculty {

    private Faculties name;
    private int studentCount;

    public Faculties getName() {
        return name;
    }

    public void setName(Faculties name) {
        this.name = name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    private Faculty(Builder builder){
        name = builder.name;
        studentCount = builder.studentCount;
    }


    public static class Builder{
        private Faculties name;
        private int studentCount;

        public Builder setStudentCount(int studentCount){
            this.studentCount = studentCount;
            return this;
        }

        public Builder setName(Faculties name){
            this.name = name;
            return this;
        }

        public Faculty build(){
            return new Faculty(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return studentCount == faculty.studentCount &&
                Objects.equals(name, faculty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, studentCount);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}
