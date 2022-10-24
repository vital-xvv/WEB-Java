package com.company.models;

public class Patient {

    private String firstName;
    private String lastName;
    private String diagnosis;
    private String dateOfBirth;
    private int doctorId;
    private String pills;
    private String procedures;
    private String operations;
    private boolean discharge;

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
}
