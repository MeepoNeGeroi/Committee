package org.example.model.entity;

import org.example.model.dao.impl.FacultyDAO;

import java.util.Objects;

public class Committee {
    private Bill bill;
    private static Committee instance;

    private Committee(){}

    public static Committee getInstance(){
        if(instance == null){
            instance = new Committee();
        }
        return instance;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return Objects.equals(bill, committee.bill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        return sb.append("Комитет: ")
                .append(bill)
                .toString();
    }
}
