package com.company;

import com.company.controllers.AdministratorController;
import com.company.controllers.DoctorController;
import com.company.models.Doctor;
import com.company.models.Patient;

public class Main {

    public static void main(String[] args) {

        AdministratorController administratorController = new AdministratorController();
        DoctorController doctorController = new DoctorController();


        doctorController.assignMedicaments(3, "Hot tea with mint");
        administratorController.seeAllPatiens("date_of_birth");

    }
}
