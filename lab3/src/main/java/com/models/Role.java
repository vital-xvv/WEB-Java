package com.models;

public enum Role {
    DOCTOR, NURSE, ADMIN, UNKNOWN;

    public static Role convertRole(String role){
        return switch (role) {
            case "ADMIN" -> Role.ADMIN;
            case "DOCTOR" -> Role.DOCTOR;
            case "NURSE" -> Role.NURSE;
            default -> Role.UNKNOWN;
        };
    }
}

