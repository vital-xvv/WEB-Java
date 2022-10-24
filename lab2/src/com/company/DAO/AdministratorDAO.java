package com.company.DAO;

import com.company.models.Doctor;
import com.company.models.Patient;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AdministratorDAO extends DAO{

    public void listOfTherapists() {

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT first_name, last_name, category FROM doctor";
            ResultSet result = statement.executeQuery(SQL);
            System.out.println("\nList of doctors");
            while(result.next()) {
                System.out.println("• " + result.getString( "first_name") + " " + result.getString("last_name")
                        + " - " + result.getString("category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void listOfTherapists(String orderBy) {

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT first_name, last_name, category FROM doctor ORDER BY " + orderBy;
            ResultSet result = statement.executeQuery(SQL);
            System.out.println("\nList of doctors");
            while(result.next()) {
                System.out.println("• " + result.getString( "first_name") + " " + result.getString("last_name")
                        + " - " + result.getString("category"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void listOfPatients() {

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM patient";
            ResultSet result = statement.executeQuery(SQL);
            System.out.println("\nList of patients");
            while(result.next()) {
                System.out.println("• " + result.getString( "first_name") + " " + result.getString("last_name")
                        + " - " + result.getDate("birth"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void listOfPatients(String orderBy) {

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM patient ORDER BY " + orderBy;
            ResultSet result = statement.executeQuery(SQL);
            System.out.println("\nList of patients");
            while(result.next()) {
                System.out.println("• " + result.getString( "first_name") + " " + result.getString("last_name")
                        + " - " + result.getString("diagnosis") + " " + result.getDate("birth") );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addPatient(Patient patient){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patient (first_name, last_name, birth) VALUES (?,?,DATE ?)");
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3,patient.getDateOfBirth());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void assignDoctorToPatient(int patientId, int doctorId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET doctor_id=? WHERE id=?");
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDoctor(Doctor doctor){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO doctor (first_name, last_name, category) VALUES (?,?,?)");
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getCategory());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setDischarge(int patientId, boolean discharge) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET discharge=? WHERE id=?");
            statement.setBoolean(1, discharge);
            statement.setInt(2, patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
