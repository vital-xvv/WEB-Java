package com.models;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    private String diagnosis;
    private String dateOfBirth;
    private int doctorId;
    private int nurseId;
    private String pills;

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    private String procedures;
    private String operations;
    private boolean discharge;

    public Patient () {

    }

    public Patient(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getPills() {
        return pills;
    }

    public void setPills(String pills) {
        this.pills = pills;
    }

    public String getProcedures() {
        return procedures;
    }

    public void setProcedures(String procedures) {
        this.procedures = procedures;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public boolean isDischarge() {
        return discharge;
    }

    public void setDischarge(boolean discharge) {
        this.discharge = discharge;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", doctorId=" + doctorId +
                ", pills='" + pills + '\'' +
                ", procedures='" + procedures + '\'' +
                ", operations='" + operations + '\'' +
                ", discharge=" + discharge +
                '}';
    }
}
