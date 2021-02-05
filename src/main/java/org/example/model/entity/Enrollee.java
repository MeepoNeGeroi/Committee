package org.example.model.entity;

import java.util.Objects;

public class Enrollee {
    private String name;
    private int age;
    private double score;
    private Faculty faculty;

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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    private Enrollee(Builder builder){
        name = builder.name;
        age = builder.age;
        score = builder.score;
    }

    public static class Builder{
        String name;
        int age;
        double score;

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Builder setScore(double score){
            this.score = score;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Enrollee build(){
            return new Enrollee(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enrollee enrollee = (Enrollee) o;
        return age == enrollee.age &&
                Double.compare(enrollee.score, score) == 0 &&
                Objects.equals(name, enrollee.name) &&
                Objects.equals(faculty, enrollee.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score, faculty);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        return sb.append("Имя: ")
                .append(name)
                .append(" возраст: ")
                .append(age)
                .append(". Средний балл: ")
                .append(score)
                .toString();
    }
}
