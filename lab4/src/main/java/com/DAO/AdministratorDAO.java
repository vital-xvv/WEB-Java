package com.DAO;

import com.models.Doctor;
import com.models.Nurse;
import com.models.Patient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AdministratorDAO extends DAO{

    private final Logger logs = Logger.getLogger(AdministratorDAO.class.getName());

    private Doctor doctorMapper(ResultSet result){
        Doctor doctor = new Doctor();
        try {
            doctor.setId(result.getInt("id"));
            doctor.setFirstName(result.getString("first_name"));
            doctor.setLastName(result.getString("last_name"));
            doctor.setCategory(result.getString("category"));
            doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
            doctor.setUsername(result.getString("username"));
        }
        catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        return doctor;
    }


    private Nurse nurseMapper(ResultSet result){
        Nurse nurse = new Nurse();
        try {
            nurse.setId(result.getInt("id"));
            nurse.setFirstName(result.getString("first_name"));
            nurse.setLastName(result.getString("last_name"));
            nurse.setSpecialization(result.getString("specialization"));
            nurse.setUsername(result.getString("username"));
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nurse;
    }


    private Patient patientMapper(ResultSet result){
        Patient patient = new Patient();
        try {
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
            patient.setNurseId(result.getInt("nurse_id"));
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patient;
    }


    public List<Doctor> listOfTherapists() {
        List<Doctor> doctors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM doctor");
            while(result.next()) {
                doctors.add(doctorMapper(result));
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
                doctors.add(doctorMapper(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doctors;
    }


    public List<Nurse> listOfNurses() {
        List<Nurse> nurses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM nurse";
            ResultSet result = statement.executeQuery(SQL);
            while(result.next()) {
                nurses.add(nurseMapper(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nurses;
    }


    public List<Doctor> listOfTherapistsByCategory(String filterByCategory) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM doctor WHERE category=?");
            statement.setString(1, filterByCategory);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                doctors.add(doctorMapper(result));
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
            ResultSet result = statement.executeQuery("SELECT * FROM patient");
            while(result.next()) {
                patients.add(patientMapper(result));
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
                patients.add(patientMapper(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }


    public List<Patient> getPatientsOfExactDoctor(int doctorId) {
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM patient WHERE doctor_id=?");
            statement.setInt(1, doctorId);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                patients.add(patientMapper(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }


    public Patient findPatientById(int id) {
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM patient WHERE id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            result.next();
            return patientMapper(result);
        } catch (SQLException e) {
            logs.info("\nPatient with id " + "'" + id + "'" + " is not found");
            throw new RuntimeException(e);
        }
    }


    public Doctor findDoctorByUsername(String username) {
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM doctor WHERE username=?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            result.next();
            return doctorMapper(result);
        } catch (SQLException e) {
            logs.info("\nDoctor profile with username " + "'" + username + "'" + " is not found");
            throw new RuntimeException(e);
        }
    }


    public void updatePrescriptions(int patientId, String diagnosis, String pills, String procedures, String operations) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET diagnosis=?, pills=?," +
                    " procedures=?, operations=? WHERE id=?");
            statement.setString(1,diagnosis);
            statement.setString(2,pills);
            statement.setString(3,procedures);
            statement.setString(4,operations);
            statement.setInt(5, patientId);
            statement.executeUpdate();
            logs.info("\nPrescriptions for patient with id " + patientId + " has been updated.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addPatient(Patient patient){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO patient (first_name, last_name, date_of_birth) VALUES (?,?,DATE ?)");
            statement.setString(1, patient.getFirstName());
            statement.setString(2, patient.getLastName());
            statement.setString(3,patient.getDateOfBirth());
            statement.executeUpdate();
            logs.info("\nPatient "+patient.getFirstName() + " " + patient.getLastName()+" created in database");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void assignDoctorToPatient(int patientId, int doctorId) {
        try {
            connection.setAutoCommit(false);
            Patient patient = findPatientById(patientId);
            PreparedStatement statement3 = connection.prepareStatement("UPDATE doctor SET amount_of_patients=amount_of_patients-1 WHERE NOT amount_of_patients=0 AND id=?");
            statement3.setInt(1, patient.getDoctorId());
            statement3.executeUpdate();
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET doctor_id=? WHERE id=?");
            statement.setInt(1, doctorId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement("UPDATE doctor SET amount_of_patients=amount_of_patients+1 WHERE id=?");
            statement2.setInt(1, doctorId);
            statement2.executeUpdate();
            connection.commit();
            logs.info("\nDoctor with id  "+doctorId + " has been assigned to patient with id " + patientId);
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            throwables.printStackTrace();
        }
    }


    public void assignNurseToPatient(int patientId, int nurseId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET nurse_id=? WHERE id=?");
            statement.setInt(1, nurseId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
            logs.info("\nNurse with id  "+nurseId + " has been assigned to patient with id " + patientId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addDoctor(Doctor doctor){
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO doctor (first_name, last_name, category, username) VALUES (?,?,?,?)");
            statement.setString(1, doctor.getFirstName());
            statement.setString(2, doctor.getLastName());
            statement.setString(3, doctor.getCategory());
            statement.setString(4, doctor.getUsername());
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES (?,?,?)");
            statement2.setString(1, doctor.getUsername());
            statement2.setString(2, "1111");
            statement2.setString(3, "DOCTOR");
            statement2.executeUpdate();
            connection.commit();
            logs.info("\nNew doctor " + doctor.getFirstName() + " " + doctor.getLastName() + " has been created in database with " +
                    "username: " + doctor.getUsername());
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            throwables.printStackTrace();
        }
    }


    public void addNurse(Nurse nurse){
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO nurse (first_name, last_name, specialization, username) VALUES (?,?,?,?)");
            statement.setString(1, nurse.getFirstName());
            statement.setString(2, nurse.getLastName());
            statement.setString(3, nurse.getSpecialization());
            statement.setString(4, nurse.getUsername());
            statement.executeUpdate();
            logs.info("\nNew nurse " + nurse.getFirstName() + " " + nurse.getLastName() + " has been created in database with " +
                    "username: " + nurse.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void addAndRegisterNurse(Nurse nurse){
        try {
            connection.setAutoCommit(false);
            addNurse(nurse);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES (?,?,?)");
            statement.setString(1, nurse.getUsername());
            statement.setString(2, "1111");
            statement.setString(3, "NURSE");
            statement.executeUpdate();
            connection.commit();
            logs.info("\nUser profile was created for nurse " + nurse.getFirstName() + " " + nurse.getLastName());
        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            throwables.printStackTrace();
        }

    }


    public void setDischarge(int patientId, boolean discharge) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET discharge=? WHERE id=?");
            statement.setBoolean(1, discharge);
            statement.setInt(2, patientId);
            statement.executeUpdate();
            logs.info("\nPatient with id " + patientId + " has been discharged from the hospital");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Nurse findNurseByUsername(String login) {
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM nurse WHERE username=?");
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            result.next();
            return nurseMapper(result);
        } catch (SQLException e) {
            logs.warning("\nUser profile with username " + login + " is not found");
            throw new RuntimeException(e);
        }
    }


    public List<Patient> getPatientsOfExactNurse(int id) {
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM patient WHERE nurse_id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                patients.add(patientMapper(result));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }

}
