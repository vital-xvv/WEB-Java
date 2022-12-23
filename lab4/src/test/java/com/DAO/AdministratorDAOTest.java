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
    void listOfTherapistsTest() {

        assertInstanceOf(ArrayList.class, administratorDAO.listOfTherapists());
        assertEquals("Duncan", administratorDAO.listOfTherapists().get(0).getFirstName());
    }


    @Test
    void listOfNurses() {
        assertInstanceOf(ArrayList.class, administratorDAO.listOfNurses());
        assertEquals("Sherry", administratorDAO.listOfNurses().get(0).getFirstName());
    }

    @Test
    void listOfTherapistsByCategory() {
        assertEquals("Duncan", administratorDAO.listOfTherapistsByCategory("psychologist").get(0).getFirstName());
        assertEquals("Max", administratorDAO.listOfTherapistsByCategory("pediatrician").get(1).getFirstName());
        assertEquals(4, administratorDAO.listOfTherapistsByCategory("psychologist").size());
        assertEquals(2, administratorDAO.listOfTherapistsByCategory("pediatrician").size());
    }

    @Test
    void findPatientById() {
        assertEquals(1, administratorDAO.findPatientById(1).getId());
        assertEquals(2, administratorDAO.findPatientById(2).getId());
        assertNotEquals(14, administratorDAO.findPatientById(14).getId());
        assertNotEquals(8, administratorDAO.findPatientById(8).getId());
        assertEquals(3, administratorDAO.findPatientById(3).getId());
        assertEquals(4, administratorDAO.findPatientById(4).getId());
    }

    @Test
    void findDoctorByUsername() {
        assertEquals("duncan.armstrong11@ukr.net",
                administratorDAO.findDoctorByUsername("duncan.armstrong11@ukr.net").getUsername());
        assertEquals("osborne.j@gmail.com",
                administratorDAO.findDoctorByUsername("osborne.j@gmail.com").getUsername());
        assertEquals("maxsteel12",
                administratorDAO.findDoctorByUsername("maxsteel12").getUsername());
        assertNotEquals("duncan.armstrong11@ukr.",
                administratorDAO.findDoctorByUsername("duncan.armstrong11@ukr.net").getUsername());
    }

    @Test
    void assignDoctorToPatient() {
        administratorDAO.assignDoctorToPatient(1,1);
        assertEquals(1, administratorDAO.findPatientById(1).getDoctorId());
        administratorDAO.assignDoctorToPatient(1,2);
        assertEquals(2, administratorDAO.findPatientById(1).getDoctorId());
        administratorDAO.assignDoctorToPatient(2,4);
        assertEquals(4, administratorDAO.findPatientById(2).getDoctorId());
        administratorDAO.assignDoctorToPatient(1,1);
        assertNotEquals(2, administratorDAO.findPatientById(1).getDoctorId());
    }

    @Test
    void assignNurseToPatient() {
        administratorDAO.assignNurseToPatient(1,1);
        assertEquals(1, administratorDAO.findPatientById(1).getNurseId());
        administratorDAO.assignNurseToPatient(1,2);
        assertEquals(2, administratorDAO.findPatientById(1).getNurseId());
        administratorDAO.assignNurseToPatient(2,4);
        assertEquals(4, administratorDAO.findPatientById(2).getNurseId());
        administratorDAO.assignNurseToPatient(1,1);
        assertNotEquals(2, administratorDAO.findPatientById(1).getNurseId());
    }


    @Test
    void setDischarge() {
        administratorDAO.setDischarge(1, true);
        assertTrue(administratorDAO.findPatientById(1).isDischarge());
        administratorDAO.setDischarge(1, false);
        assertFalse(administratorDAO.findPatientById(1).isDischarge());
        administratorDAO.setDischarge(2, true);
        assertTrue(administratorDAO.findPatientById(2).isDischarge());
        administratorDAO.setDischarge(2, false);
        assertFalse(administratorDAO.findPatientById(2).isDischarge());
    }

    @Test
    void findNurseByUsername() {
        assertEquals("shbronxx@gmail.com", administratorDAO.findNurseByUsername("shbronxx@gmail.com").getUsername());
        assertEquals("jack-swift", administratorDAO.findNurseByUsername("jack-swift").getUsername());
        assertEquals("margo-cruzZ", administratorDAO.findNurseByUsername("margo-cruzZ").getUsername());
        assertNotEquals("jack-swif", administratorDAO.findNurseByUsername("jack-swif").getUsername());
    }
}