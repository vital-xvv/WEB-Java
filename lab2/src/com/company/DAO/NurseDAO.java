package com.company.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NurseDAO extends DAO{

    public void definePills(int patientID, String pills) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET pills=? WHERE id=?");
            statement.setString(1, pills);
            statement.setInt(2, patientID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void defineProcedures(int patientID, String procedures) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET procedures=? WHERE id=?");
            statement.setString(1, procedures);
            statement.setInt(2, patientID);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
