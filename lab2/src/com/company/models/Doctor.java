package com.company.models;

public class Doctor {

    private int id;
    private String firstName;
    private String lastName;
    private String category;
    private int amountOfPatients;

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
