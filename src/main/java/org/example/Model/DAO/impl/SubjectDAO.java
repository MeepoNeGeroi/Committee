package org.example.Model.DAO.impl;

import org.example.Model.DAO.DAO;
import org.example.Model.Entity.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAO<Subject> {

    private List<Subject> subjects = new ArrayList<>();

    @Override
    public List<Subject> getAll() {
        return subjects;
    }

    @Override
    public void read() {

    }

    @Override
    public void update(Subject subject, String[] params) {

    }

    @Override
    public void delete(Subject subject) {
        subjects.remove(subject);
    }
}
