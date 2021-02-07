package org.example.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Administrator {
    private List<Enrollee> enrollees = new ArrayList<>();

    public List<Enrollee> getEnrollee() {
        return enrollees;
    }

    public void setEnrollee(List<Enrollee> enrollee) {
        this.enrollees = enrollee;
    }

    private Administrator(Builder builder){
        enrollees.add(builder.enrollee);
    }

    public static class Builder{
        private Enrollee enrollee;

        public Builder setEnrollee(Enrollee enrollee){
            this.enrollee = enrollee;
            return this;
        }

        public Administrator build(){
            return new Administrator(this);
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(50);
        return sb.append("Студент: ")
                .append(enrollees)
                .append("; факультет: ")
                .toString();
    }
}