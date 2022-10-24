package com.company;

import com.company.DAO.AdministratorDAO;
import com.company.models.Doctor;
import com.company.models.Patient;

public class Main {

    public static void main(String[] args) {
        AdministratorDAO admin = new AdministratorDAO();


//        Doctor doc = new Doctor();
//        doc.setFirstName("Steven");
//        doc.setLastName("Phillips");
//        doc.setCategory("dentist");
//        admin.addDoctor(doc);
//        admin.setDischarge(1, true);

        admin.listOfTherapists();
        admin.listOfPatients("birth");


    }
}
