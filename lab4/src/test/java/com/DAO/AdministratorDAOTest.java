package com.DAO;

import com.models.Doctor;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorDAOTest {

    private final AdministratorDAO administratorDAO = new AdministratorDAO();


    @Test
    void listOfTherapists() {

        assertInstanceOf(ArrayList.class, administratorDAO.listOfTherapists());
    }

    @Test
    void testListOfTherapists() {
    }

    @Test
    void listOfNurses() {
    }

    @Test
    void listOfTherapistsByCategory() {
    }

    @Test
    void listOfPatients() {
    }

    @Test
    void testListOfPatients() {
    }

    @Test
    void getPatientsOfExactDoctor() {
    }

    @Test
    void findPatientById() {
    }

    @Test
    void findDoctorByUsername() {
    }

    @Test
    void updatePrescriptions() {
    }

    @Test
    void addPatient() {
    }

    @Test
    void assignDoctorToPatient() {
    }

    @Test
    void assignNurseToPatient() {
    }

    @Test
    void addDoctor() {
    }

    @Test
    void addNurse() {
    }

    @Test
    void addAndRegisterNurse() {
    }

    @Test
    void setDischarge() {
    }

    @Test
    void findNurseByUsername() {
    }

    @Test
    void getPatientsOfExactNurse() {
    }
}