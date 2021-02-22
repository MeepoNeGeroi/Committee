package org.example.model.service;

import org.example.model.dao.impl.CommitteeDAO;
import org.example.model.entity.*;
import org.example.model.enumeration.Faculties;
import org.example.model.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class EnrolledStudentsService {
    private static EnrolledStudentsService instance;

    private EnrolledStudentsService(){}

    public static EnrolledStudentsService getInstance(){
        if(instance == null){
            instance = new EnrolledStudentsService();
        }

        return instance;
    }

    public List<Enrollee> getEnrolledStudents(Faculties faculty) throws ServiceException {
        try {
            Committee committee = CommitteeDAO.getInstance().read();
            List<Enrollee> enrollees = new ArrayList<>();

            List<Enrollee> enrolledStudents = committee.getBill().getAdministrator().getEnrollee();
            List<Enrollee> sortedByFaculty = sortedByFaculties(enrolledStudents, faculty);
            List<Enrollee> sortedByScore = sortedByScore(sortedByFaculty);

            for (int i = 0; i < sortedByScore.size() && i < Faculties.getCount(faculty.toString()); i++) {
                enrollees.add(sortedByScore.get(i));
            }

            return enrollees;
        }
        catch (Exception e){
            throw new ServiceException();
        }
    }

    private List<Enrollee> sortedByScore(List<Enrollee> enrollees) {
        Enrollee buf;

        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < enrollees.size() - 1; i++) {
                if(enrollees.get(i) != null) {
                    if (averageSum(enrollees.get(i).getCertificate()) <
                            averageSum(enrollees.get(i + 1).getCertificate())) {
                        isSorted = false;
                        buf = enrollees.get(i);
                        enrollees.set(i, enrollees.get(i + 1));
                        enrollees.set(i + 1, buf);
                    }
                }
            }
        }

        return enrollees;
    }

    private List<Enrollee> sortedByFaculties(List<Enrollee> enrollees, Faculties faculty){
        List<Enrollee> sortedByFaculties = new ArrayList<>();

        for(int i = 0; i < enrollees.size(); i++){
            if(enrollees.get(i) != null) {
                if (enrollees.get(i).getFaculty().getName().equals(faculty)) {
                    sortedByFaculties.add(enrollees.get(i));
                }
            }
        }

        return sortedByFaculties;
    }

    private double averageSum(Certificate certificate){
        return certificate.getAverageScore()
                + certificate.getFirstSubject()
                + certificate.getSecondSubject()
                + certificate.getThirdSubject();
    }
}