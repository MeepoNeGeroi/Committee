package org.example.Model;

import java.util.List;

public enum Faculties {
    поит,
    тэрэс,
    минтис;

    public static int getCount(String faculty){
        switch(faculty.toLowerCase()){
            case "поит":
                return 2;
            case "тэрэс" :
                return 2;
            case "минтис":
                return 2;
            default: return 0;
        }
    }
}
