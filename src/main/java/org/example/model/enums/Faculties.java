package org.example.model.enums;

public enum Faculties {
    poit,
    teres,
    mintis;

    public static int getCount(String faculty){
        switch(faculty.toLowerCase()){
            case "poit":
                return 2;
            case "teres" :
                return 2;
            case "mintis":
                return 2;
            default: return 0;
        }
    }
}
