package com.models;

public class Nurse {

    private int id;
    private String firstName;
    private String lastName;
    private String specialization;

    private String username;

    public Nurse(String firstName, String lastName, String specialization, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.username = username;
    }

    public Nurse() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
