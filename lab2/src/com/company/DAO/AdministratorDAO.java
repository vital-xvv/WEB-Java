package com.company.DAO;

import com.company.models.Doctor;
import com.company.models.Nurse;
import com.company.models.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministratorDAO extends DAO{

    public List<Doctor> listOfTherapists() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM doctor";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(result.getInt("id"));
                doctor.setFirstName(result.getString("first_name"));
                doctor.setLastName(result.getString("last_name"));
                doctor.setCategory(result.getString("category"));
                doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
                doctors.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }

    public List<Doctor> listOfTherapists(String orderBy) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM doctor ORDER BY " + orderBy;
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(result.getInt("id"));
                doctor.setFirstName(result.getString("first_name"));
                doctor.setLastName(result.getString("last_name"));
                doctor.setCategory(result.getString("category"));
                doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
                doctors.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }

    public List<Doctor> listOfTherapistsByCategory(String filterByCategory) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM doctor WHERE category=?");
            statement.setString(1, filterByCategory);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(result.getInt("id"));
                doctor.setFirstName(result.getString("first_name"));
                doctor.setLastName(result.getString("last_name"));
                doctor.setCategory(result.getString("category"));
                doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
                doctors.add(doctor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }

    public List<Patient> listOfPatients() {
        List<Patient> patients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM patient";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                Patient patient = new Patient();
                patient.setId(result.getInt("id"));
                patient.setDateOfBirth(result.getString("date_of_birth"));
                patient.setFirstName(result.getString("first_name"));
                patient.setLastName(result.getString("last_name"));
                patient.setDischarge(result.getBoolean("discharge"));
                patient.setDiagnosis(result.getString("diagnosis"));
                patient.setPills(result.getString("pills"));
                patient.setOperations(result.getString("operations"));
                patient.setProcedures(result.getString("procedures"));
                patient.setDoctorId(result.getInt("doctor_id"));
                patients.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

    public List<Patient> listOfPatients(String orderBy) {
        List<Patient> patients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM patient ORDER BY " + orderBy;
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                Patient patient = new Patient();
                patient.setId(result.getInt("id"));
                patient.setDateOfBirth(result.getString("date_of_birth"));
                patient.setFirstName(result.getString("first_name"));
                patient.setLastName(result.getString("last_name"));
                patient.setDischarge(result.getBoolean("discharge"));
                patient.setDiagnosis(result.getString("diagnosis"));
                patient.setPills(result.getString("pills"));
                patient.setOperations(result.getString("operations"));
                patient.setProcedures(result.getString("procedures"));
                patient.setDoctorId(result.getInt("doctor_id"));
                patients.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

    public void addPatient(Patient patient){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patient (first_name, last_name, date_of_birth) VALUES (?,?,DATE ?)");
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

    public void addNurse(Nurse nurse){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO nurse (first_name, last_name, specialization) VALUES (?,?,?)");
            statement.setString(1, nurse.getFirstName());
            statement.setString(2, nurse.getLastName());
            statement.setString(3, nurse.getSpecialization());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int setDischarge(int patientId, boolean discharge) {
        int truth = 1;
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET discharge=? WHERE (id=?) AND (diagnosis IS NOT NULL)");
            statement.setBoolean(1, discharge);
            statement.setInt(2, patientId);
            truth = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return truth;
    }


}
