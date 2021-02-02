package org.example.Model.Entity;

public class Bill {

    private Enrollee enrollee;
    private Faculty faculty;

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public Faculty getFaculty() {
        return faculty;
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
    public String toString() {
        return "Bill{" +
                "enrollee=" + enrollee +
                ", faculty=" + faculty +
                '}';
    }
}
