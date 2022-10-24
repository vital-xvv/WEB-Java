package com.company.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorDAO extends NurseDAO{

    public void defineDiagnosis(int patientID, String diagnosis) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET diagnosis=? WHERE id=?");
            statement.setString(1, diagnosis);
            statement.setInt(2, patientID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void defineOperations(int patientID, String operations) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET operations=? WHERE id=?");
            statement.setString(1, operations);
            statement.setInt(2, patientID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
