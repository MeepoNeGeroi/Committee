package org.example;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.example.controller.command.EnrolleeCommand;
import org.example.model.dao.exception.DAOException;
import org.example.model.dao.impl.AdministratorDAO;
import org.example.model.dao.impl.EnrolleeDAO;
import org.example.model.entity.Administrator;
import org.example.model.entity.Enrollee;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}