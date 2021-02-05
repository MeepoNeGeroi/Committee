package org.example.model.entity;

import java.util.Objects;

public class Bill {

    private Enrollee enrollee;
    private Faculty faculty;

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    private Bill(Builder builder){
        enrollee = builder.enrollee;
        faculty = builder.faculty;
    }

    public static class Builder{
        Enrollee enrollee;
        Faculty faculty;

        public Builder setEnrollee(Enrollee enrollee){
            this.enrollee = enrollee;
            return this;
        }

        public Builder setFaculty(Faculty faculty){
            this.faculty = faculty;
            return this;
        }

        public Bill build(){
            return new Bill(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(enrollee, bill.enrollee) &&
                Objects.equals(faculty, bill.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollee, faculty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        return sb.append("Студент: ")
                .append(enrollee)
                .append("; факультет: ")
                .append(faculty)
                .toString();
    }
}
