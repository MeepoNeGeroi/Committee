package org.example.model.entity;

import java.util.Objects;

public class Bill {
    private Administrator administrator;

    private static Bill instance;

    private Bill(){}

    public static Bill getInstance(){
        if(instance == null){
            instance = new Bill();
        }
        return instance;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator administrator = (Administrator) o;
        return this.administrator == administrator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(administrator);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        return sb.append("Администратор: ")
                .append(administrator)
                .toString();
    }

}
