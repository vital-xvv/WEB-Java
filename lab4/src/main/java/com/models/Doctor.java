package com.models;

public class Doctor {
    public Doctor(String firstName, String lastName, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
    }

    public Doctor(int id , String firstName, String lastName, String category) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
    }
    public Doctor() {
    }

    private int id;
    private String firstName;
    private String lastName;
    private String category;
    private int amountOfPatients;

    private String username;

    public Doctor(String firstName, String lastName, String category, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
        this.username = username;
    }

    public int getAmountOfPatients() {
        return amountOfPatients;
    }

    public void setAmountOfPatients(int amountOfPatients) {
        this.amountOfPatients = amountOfPatients;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", category='" + category + '\'' +
                ", amountOfPatients=" + amountOfPatients +
                '}';
    }
}
