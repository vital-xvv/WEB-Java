package com.controllers;

import com.DAO.AdministratorDAO;
import com.models.Doctor;
import com.models.Nurse;
import com.models.Patient;

import java.util.List;

public class AdministratorController {

    private static final AdministratorDAO administratorDAO = new AdministratorDAO();
    private static AdministratorController admin;

    public static AdministratorController getAdministratorController() {
        if(admin == null) return new AdministratorController();
        return admin;
    }

    private AdministratorController() {

    }


    public void registerNewPatient(Patient patient) {
        administratorDAO.addPatient(patient);
        System.out.println("A new patient " + patient.getFirstName() + " " + patient.getLastName() +"has been hospitalized");
    }


    public void hireNewDoctor(Doctor doctor){
        administratorDAO.addDoctor(doctor);
        System.out.println("A new doctor " + doctor.getFirstName() + " " + doctor.getLastName() +"has been hired");
    }


    public void hireNewNurse(Nurse nurse){
        administratorDAO.addNurse(nurse);
        System.out.println("A new nurse " + nurse.getFirstName() + " " + nurse.getLastName() +"has been hired");
    }


    public void seeAllDoctors(String orderBy) {
        List<Doctor> doctors = administratorDAO.listOfTherapists(orderBy);
        System.out.println("\nList of doctors");
        doctors.forEach(System.out::println);
    }


    public void seeAllDoctorsByCategory(String category) {
        List<Doctor> doctors = administratorDAO.listOfTherapistsByCategory(category);
        System.out.println("\nList of doctors by category '" + category + "'");
        doctors.forEach(System.out::println);
    }


    public void seeAllDoctors() {
        List<Doctor> doctors = administratorDAO.listOfTherapists();
        System.out.println("\nList of doctors");
        doctors.forEach(System.out::println);
    }


    public void seeAllPatiens() {
        List<Patient> patients = administratorDAO.listOfPatients();
        System.out.println("\nList of patients");
        patients.forEach(System.out::println);
    }


    public void seeAllPatiens(String orderBy) {
        List<Patient> patients = administratorDAO.listOfPatients(orderBy);
        System.out.println("\nList of patients");
        patients.forEach(System.out::println);
    }


    public void assignTreatingDoctorToPatient(int patientID, int doctorID){
        administratorDAO.assignDoctorToPatient(patientID, doctorID);
        System.out.println("A new doctor will be treating patient with id: " + patientID);
    }


    public void dischargePatient(int patientID){
        int truth = administratorDAO.setDischarge(patientID, true);
        System.out.println("A patient with id: " + patientID + " is " + (truth == 0? "not ": "") +"discharged from the hospital");
    }


}
