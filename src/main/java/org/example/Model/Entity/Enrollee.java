package org.example.Model.Entity;

public class Enrollee {
    String name;
    int age;
    double score;
    Faculty faculty;

    private Enrollee(Builder builder){
        name = builder.name;
        age = builder.age;
        score = builder.score;
    }

    public String getName() {
        return name;
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

    public void setScore(int score) {
        this.score = score;
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
    public String toString() {
        return "Enrollee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
