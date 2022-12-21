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
                doctor.setUsername(result.getString("username"));
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
                doctor.setUsername(result.getString("username"));
                doctors.add(doctor);
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
                Nurse nurse = new Nurse();
                nurse.setId(result.getInt("id"));
                nurse.setFirstName(result.getString("first_name"));
                nurse.setLastName(result.getString("last_name"));
                nurse.setSpecialization(result.getString("specialization"));
                nurse.setUsername(result.getString("username"));
                nurses.add(nurse);
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
                Doctor doctor = new Doctor();
                doctor.setId(result.getInt("id"));
                doctor.setFirstName(result.getString("first_name"));
                doctor.setLastName(result.getString("last_name"));
                doctor.setCategory(result.getString("category"));
                doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
                doctor.setUsername(result.getString("username"));
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
                patient.setNurseId(result.getInt("nurse_id"));
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
                patient.setNurseId(result.getInt("nurse_id"));
                patients.add(patient);
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
                patient.setNurseId(result.getInt("nurse_id"));
                patients.add(patient);
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
            Patient patient = new Patient();
            result.next();
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
            return patient;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Doctor findDoctorByUsername(String username) {
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM doctor WHERE username=?");
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();
            Doctor doctor = new Doctor();
            result.next();
            doctor.setId(result.getInt("id"));
            doctor.setFirstName(result.getString("first_name"));
            doctor.setLastName(result.getString("last_name"));
            doctor.setCategory(result.getString("category"));
            doctor.setAmountOfPatients(result.getInt("amount_of_patients"));
            doctor.setUsername(result.getString("username"));
            return doctor;
        } catch (SQLException e) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void assignDoctorToPatient(int patientId, int doctorId) {
        try {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void assignNurseToPatient(int patientId, int nurseId) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE patient SET nurse_id=? WHERE id=?");
            statement.setInt(1, nurseId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addDoctor(Doctor doctor){
        try {
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
        } catch (SQLException throwables) {
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addAndRegisterNurse(Nurse nurse) {
        addNurse(nurse);
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user (login, password, role) VALUES (?,?,?)");
            statement.setString(1, nurse.getUsername());
            statement.setString(2, "1111");
            statement.setString(3, "NURSE");
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


    public Nurse findNurseByUsername(String login) {

        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM nurse WHERE username=?");
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();
            Nurse nurse = new Nurse();
            result.next();
            nurse.setId(result.getInt("id"));
            nurse.setFirstName(result.getString("first_name"));
            nurse.setLastName(result.getString("specialization"));
            nurse.setUsername(result.getString("username"));
            return nurse;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Patient> getPatientsOfExactNurse(int id) {
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement statement= connection.prepareStatement("SELECT * FROM patient WHERE nurse_id=?");
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
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
                patient.setNurseId(result.getInt("nurse_id"));
                patients.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return patients;
    }
}
