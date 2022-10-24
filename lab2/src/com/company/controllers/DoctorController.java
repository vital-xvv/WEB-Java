package com.company.controllers;

import com.company.DAO.DoctorDAO;

public class DoctorController extends NurseController {

    private static final DoctorDAO doctorDAO = new DoctorDAO();

    public void defineDiagnosis(int patientID, String diagnosis){
        doctorDAO.defineDiagnosis(patientID, diagnosis);
        System.out.println("A patient with ID: " + patientID + " is diagnosed with " + diagnosis);
    }

    public void assignOperation(int patientID, String operation){
        doctorDAO.defineOperations(patientID, operation);
        System.out.println("A patient with ID: " + patientID + " will have  " + operation);
    }

}
