package org.example.model.entity;

import java.util.Objects;

public class Certificate {
    private double averageScore;
    private int firstSubject;
    private int secondSubject;
    private int thirdSubject;

    private Certificate(Builder builder){
        averageScore = builder.averageScore;
        firstSubject = builder.firstSubject;
        secondSubject = builder.secondSubject;
        thirdSubject = builder.thirdSubject;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getFirstSubject() {
        return firstSubject;
    }

    public void setFirstSubject(int firstSubject) {
        this.firstSubject = firstSubject;
    }

    public int getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(int secondSubject) {
        this.secondSubject = secondSubject;
    }

    public int getThirdSubject() {
        return thirdSubject;
    }

    public void setThirdSubject(int thirdSubject) {
        this.thirdSubject = thirdSubject;
    }

    public static class Builder{
        private double averageScore;
        private int firstSubject;
        private int secondSubject;
        private int thirdSubject;

        public Builder(){}

        public Builder setAverageScore(double averageScore){
            this.averageScore = averageScore;
            return this;
        }

        public Builder setFirstSubject(int firstSubject){
            this.firstSubject = firstSubject;
            return this;
        }

        public Builder setSecondSubject(int secondSubject){
            this.secondSubject = secondSubject;
            return this;
        }

        public Builder setThirdSubject(int thirdSubject){
            this.thirdSubject = thirdSubject;
            return this;
        }

        public Certificate build(){
            return new Certificate(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Certificate certificate = (Certificate) o;
        return averageScore == certificate.averageScore &&
                firstSubject == certificate.firstSubject &&
                secondSubject == certificate.secondSubject &&
                thirdSubject == certificate.thirdSubject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(averageScore, firstSubject, secondSubject, thirdSubject);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append("Средний балл: ")
                .append(averageScore)
                .append(". Отметки по предметам: : ")
                .append(firstSubject)
                .append(" + ")
                .append(secondSubject)
                .append(" + ")
                .append(thirdSubject)
                .toString();
    }
}
