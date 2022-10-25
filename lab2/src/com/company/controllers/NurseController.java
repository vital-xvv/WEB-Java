package com.company.controllers;

import com.company.DAO.NurseDAO;

public class NurseController {

    private static final NurseDAO nurseDAO = new NurseDAO();
    private static NurseController nurseController;

    private NurseController(){

    }

    public static NurseController getNurseController(){
        if(nurseController== null) return new NurseController();
        return nurseController;
    }

    public void assignMedicaments(int patientID, String medicaments){
        nurseDAO.definePills(patientID, medicaments);
        System.out.println(medicaments + " has been assigned for patient with ID: " + patientID);
    }

    public void assignProcedures(int patientID, String procedures){
        nurseDAO.defineProcedures(patientID, procedures);
        System.out.println(procedures + " has been assigned for patient with ID: " + patientID);
    }
}
